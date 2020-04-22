module.exports = {
  packagerConfig: {},
  makers: [
        {
          name: '@electron-forge/maker-squirrel',
          config: {
            name: 'electron_update_app'
          }
        }
  ],
  publishers: [
      {
        name: '@electron-forge/publisher-github',
        config: {
            repository: {
                owner: 'csteyrer',
                name: 'ElectronUpdateApp'
            },
            prerelease: false,
            draft: false
        }
      }
  ]
}