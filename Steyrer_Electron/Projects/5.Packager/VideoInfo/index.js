const electron = require('electron');
const ffmpeg = require('fluent-ffmpeg');
const path = require('path');

const {app, BrowserWindow, ipcMain} = electron;

let mainWindow;

app.on('ready', () => {
    console.log('App is now ready');

    mainWindow = new BrowserWindow({
        webPreferences: {
            contextIsolation: true,
            nodeIntegration: false,
            enableRemoteModule: false,
            preload: path.join(__dirname, 'preload.js')
        }
    });
    
    //mainWindow.loadURL('http://google.com');
    //mainWindow.loadURL(`file://${__dirname}/index.html`);
    mainWindow.loadFile('index.html');

});

ipcMain.on('video:submit', (event, filePath) => {
    ffmpeg.ffprobe(filePath, (err, metadata) => {
        mainWindow.webContents.send(
            'video:metadata',
            metadata.format.duration
        );
    });
});