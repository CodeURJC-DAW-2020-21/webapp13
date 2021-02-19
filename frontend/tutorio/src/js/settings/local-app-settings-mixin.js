export default {
  props: {
    themeActive: {
      type: String,
      required: true
    },
    themeLocation: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      buttonAlign: 'right',
      drawerAlign: 'end',
      options: [
        {
          id: 'layout',
          title: 'Layout',
          children: [
            {
              id: 'theme',
              title: 'Theme',
              component: 'form-image-group',
              cookies: false,
              value: 'blue-light',
              options: [
                {
                  text: 'Blue Dark',
                  value: 'blue-dark',
                  image: 'assets/images/settings-blue-dark.png',
                  selected: true
                },
                {
                  text: 'Blue Light',
                  value: 'blue-light',
                  image: 'assets/images/settings-blue-light.png'
                },
                {
                  text: 'Teal Dark',
                  value: 'teal-dark',
                  image: 'assets/images/settings-teal-dark.png'
                },
                {
                  text: 'Teal Light',
                  value: 'teal-light',
                  image: 'assets/images/settings-teal-light.png'
                }
              ]
            },
            {
              id: 'rtl',
              title: 'RTL',
              component: 'custom-checkbox-toggle',
              options: [
                {
                  value: true
                },
                {
                  value: false,
                  selected: true
                }
              ]
            }
          ]
        },
        {
          id: 'mainDrawer',
          title: 'Main Drawer',
          children: [
            {
              id: 'align',
              title: 'Align',
              component: 'b-form-radio-group',
              options: [
                {
                  text: 'Start',
                  value: 'start',
                  selected: true
                },
                {
                  text: 'End',
                  value: 'end'
                }
              ]
            },
            {
              id: 'sidebar',
              title: 'Sidebar Skin',
              component: 'b-form-radio-group',
              options: [
                {
                  text: 'Dark',
                  value: 'dark',
                  selected: true
                },
                {
                  text: 'Light',
                  value: 'light'
                }
              ]
            }
          ]
        },
        {
          id: 'mainNavbar',
          title: 'Main Navbar',
          children: [
            {
              id: 'navbar',
              title: 'Main Navbar',
              component: 'b-form-radio-group',
              options: [
                {
                  text: 'Dark',
                  value: 'dark',
                  selected: true
                },
                {
                  text: 'Light',
                  value: 'light'
                }
              ]
            }
          ]
        }
      ],
      config: {
        'layout.theme': function(theme) {
          if (theme !== this.themeActive) {
            location = this.themeLocation[theme]
          }
        },
        'layout.rtl': function(rtl) {
          document.querySelector('html').setAttribute('dir', rtl ? 'rtl' : 'ltr')

          ;[...document.querySelectorAll('.mdk-drawer')]
            .forEach(node => this.try(node, function() {
              this.mdkDrawer._resetPosition()
            }))

          ;[...document.querySelectorAll('.mdk-drawer-layout')]
            .forEach(node => this.try(node, function() {
              this.mdkDrawerLayout._resetLayout()
            }))
        },
        'mainDrawer.align': function(align) {
          this.try(document.querySelector('#default-drawer'), function() {
            this.mdkDrawer.align = align
          })
        },
        'mainDrawer.sidebar': {
          'light': {
            '#default-drawer .sidebar-brand img': {
              src: 'assets/images/logo/black-100@2x.png',
            },
            '#default-drawer .sidebar': {
              addClass: ['sidebar-light'],
              removeClass: ['sidebar-dark', 'bg-dark']
            },
            '#default-drawer .search-form': {
              addClass: ['search-form--light'],
              removeClass: ['search-form--black']
            }
          },
          'dark': {
            '#default-drawer .sidebar-brand img': {
              src: 'assets/images/logo/white-100@2x.png',
            },
            '#default-drawer .sidebar': {
              addClass: ['sidebar-dark', 'bg-dark'],
              removeClass: ['sidebar-light', 'bg-white']
            },
            '#default-drawer .search-form': {
              addClass: ['search-form--black'],
              removeClass: ['search-form--light']
            }
          }
        },
        'mainNavbar.navbar': {
          'light': {
            '#default-navbar .navbar-brand img': {
              src: 'assets/images/logo/black-100@2x.png',
            },
            '#default-navbar': {
              addClass: ['navbar-light', 'bg-white'],
              removeClass: ['navbar-dark', 'bg-dark']
            },
            '#default-navbar [data-toggle=modal]': {
              addClass: ['btn-white'],
              removeClass: ['btn-black']
            },
            '#default-navbar .search-form': {
              addClass: ['search-form--light'],
              removeClass: ['search-form--black']
            }
          },
          'dark': {
            '#default-navbar .navbar-brand img': {
              src: 'assets/images/logo/white-100@2x.png',
            },
            '#default-navbar': {
              addClass: ['navbar-dark', 'bg-dark'],
              removeClass: ['navbar-light', 'bg-white']
            },
            '#default-navbar [data-toggle=modal]': {
              addClass: ['btn-black'],
              removeClass: ['btn-white']
            },
            '#default-navbar .search-form': {
              addClass: ['search-form--black'],
              removeClass: ['search-form--light']
            }
          }
        }
      }
    }
  },
  computed: {
    computedOptions() {
      const options = JSON.parse(JSON.stringify(this.options))
      options.map(option => {
        option.children
          .filter(group => group.cookies === false)
          .map(group => {
            if (this.themeActive) {
              group.options.map(go => go.selected = go.value === this.themeActive)
            } else {
              group.options.map(go => go.selected = go.value === group.value)
            }
          })
      })

      return options
    },
    localDrawerAlign() {
      let drawerAlign = this.drawerAlign
      try {
        drawerAlign = this.settings['mainDrawer.align'] === 'end' ? 'start' : 'end'
      } catch(e) {}

      return drawerAlign
    },
    localButtonAlign() {
      let buttonAlign = this.buttonAlign
      
      try {
        buttonAlign = this.settings['mainDrawer.align'] === 'end' ? 'left' : 'right'
        if (this.settings['layout.rtl']) {
          buttonAlign = this.settings['mainDrawer.align'] === 'end' ? 'right' : 'left'
        }
      } catch(e) {}

      return buttonAlign
    }
  },
  created() {
    this.listenOnRoot('fm:settings:state', this.onUpdate)
  },
  methods: {
    try(node, callback) {
      try {
        callback.call(node)
      } catch(e) {
        node.addEventListener('domfactory-component-upgraded', callback)
      }
    }
  }
}
