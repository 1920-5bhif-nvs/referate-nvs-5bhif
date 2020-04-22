const electron = require('electron');
const path = require('path');

const {app, BrowserWindow, Menu, ipcMain} = electron;
const isMac = process.platform === 'darwin';
const isNotProduction = process.env.NODE_ENV !== 'production';
const ipcWebPreferences =  {
    contextIsolation: true,
    nodeIntegration: false,
    enableRemoteModule: false,
    preload: path.join(__dirname, 'preload.js')
};

let mainWindow;
let addWindow;

app.on('ready', () => {
    mainWindow = new BrowserWindow({
        webPreferences: ipcWebPreferences
    });

    mainWindow.loadFile('main.html');
    mainWindow.on('closed', () => app.quit());

    const mainMenu = Menu.buildFromTemplate(menuTemplate);
    Menu.setApplicationMenu(mainMenu);


});

ipcMain.on('todo:add', (event, todo) => {
    mainWindow.webContents.send('todo:add', todo);
    addWindow.close();
});

function createAddWindow(){
    addWindow = new BrowserWindow({
        width: 300,
        height: 200,
        title: 'Add New Todo',
        webPreferences: ipcWebPreferences
    });

    addWindow.loadFile('add.html');
    addWindow.on('closed', () => addWindow = null);
    
    if(!isMac){
        if(isNotProduction){
            const addMenuTemplate = [];
            addMenuTemplate.push(developerMenu);
            addWindow.setMenu(Menu.buildFromTemplate(addMenuTemplate));
        }
        else{
            addWindow.removeMenu();
        }
    }

}

const menuTemplate = [
    {
        label: 'File',
        submenu: [
            { 
                label: 'New Todo',
                click()
                {
                    if(addWindow == null){
                        createAddWindow();
                    }
                    else{
                        addWindow.focus();
                    }
                }
            },
            { 
                label: 'Clear Todos',
                click()
                {
                    mainWindow.webContents.send('todo:clear');
                }
            },
            { 
                label: 'Quit',
                accelerator: isMac ? 'Command+Q' : 'Ctrl+Q',
                click(){
                    app.quit();
                }
            }
        ]
    }  
];


if(isMac){
    menuTemplate.unshift({role: 'appMenu'});
}

const developerMenu = {
    label: 'Developer',
    submenu: [
        { role: 'reload'},
        {
            label: 'Toggle Developer Tools',
            accelerator: isMac ? 'Command+Alt+I' : 'Ctrl+Shift+I',
            click(item, focusedWindow){
                focusedWindow.toggleDevTools();
            }
        }
    ]
}
//, 
//{ role: 'fileMenu' },
//{ role: 'editMenu' },
//{ role: 'viewMenu' },
//{ role: 'windowMenu' }
;

if(isNotProduction){
    menuTemplate.push(
        developerMenu
    );
}