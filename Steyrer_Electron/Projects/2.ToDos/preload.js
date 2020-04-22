const { contextBridge, ipcRenderer } = require("electron");

// Expose protected methods that allow the renderer process to use
// the ipcRenderer without exposing the entire object
contextBridge.exposeInMainWorld(
    "electron", {
        ipcRenderer: {
            send: (eventName, data) => {
                ipcRenderer.send(eventName, data);
            },
            on: (eventName, func) => {
                ipcRenderer.on(eventName, func);
            }
        }
    }
);