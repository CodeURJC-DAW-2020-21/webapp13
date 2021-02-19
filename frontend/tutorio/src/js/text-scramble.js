class TextScramble {
  constructor(el) {
    this.el = el
    this.chars = '!<>-_\\/[]{}—=+*^?#________'
    this.update = this.update.bind(this)
  }
  setText(newText) {
    const oldText = this.el.innerText
    const length = Math.max(oldText.length, newText.length)
    const promise = new Promise((resolve) => this.resolve = resolve)
    this.queue = []
    for (let i = 0; i < length; i++) {
      const from = oldText[i] || ''
      const to = newText[i] || ''
      const start = Math.floor(Math.random() * 40)
      const end = start + Math.floor(Math.random() * 40)
      this.queue.push({ from, to, start, end })
    }
    cancelAnimationFrame(this.frameRequest)
    this.frame = 0
    this.update()
    return promise
  }
  update() {
    let output = ''
    let complete = 0
    for (let i = 0, n = this.queue.length; i < n; i++) {
      let { from, to, start, end, char } = this.queue[i]
      if (this.frame >= end) {
        complete++
        output += to
      } else if (this.frame >= start) {
        if (!char || Math.random() < 0.28) {
          char = this.randomChar()
          this.queue[i].char = char
        }
        output += `<span class="text-scramble__dud">${char}</span>`
      } else {
        output += from
      }
    }
    this.el.innerHTML = output
    if (complete === this.queue.length) {
      this.resolve()
    } else {
      this.frameRequest = requestAnimationFrame(this.update)
      this.frame++
    }
  }
  randomChar() {
    return this.chars[Math.floor(Math.random() * this.chars.length)]
  }
}

const textScrambleComponent = () => {
  let phrases = [
    'Development',
    'Design',
    'Business',
    'Photography'
  ]
  let timer
  let interval = 2000

  return {
    observers: [
      '_reset(phrases)'
    ],

    listeners: [
      'document._onVisibilityChange(visibilitychange)'
    ],

    get phrases () {
      return phrases
    },

    set phrases (value) {
      phrases = value
    },

    _isOnScreen () {
      var rect = this.element.getBoundingClientRect()
      return rect.top >= 0 &&
        rect.left >= 0 &&
        rect.bottom <= window.innerHeight &&
        rect.right <= window.innerWidth
    },

    _onVisibilityChange () {
      this[document.hidden ? 'destroy' : 'start']()
    },

    start () {
      const fx = new TextScramble(this.element)

      let counter = 0
      const next = () => {
        if (!this._isOnScreen()) {
          timer = setTimeout(next, interval)
          return
        }

        fx.setText(this.phrases[counter]).then(() => {
          timer = setTimeout(next, interval)
        })
        counter = (counter + 1) % this.phrases.length
      }

      next()
    },

    init () {
      this.start()
    },

    destroy () {
      timer = clearTimeout(timer)
    },

    _reset () {
      this.destroy()
      this.start()
    }
  }
}

domFactory.handler.register('text-scramble', textScrambleComponent)