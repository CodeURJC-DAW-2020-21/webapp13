import hljs from 'highlight.js';
import 'highlight.js/styles/github.css'

(function() {
  'use strict';
  
  $(document).ready(function() {
    $('.highlight').each(function(i, block) {
      hljs.highlightBlock(block)
    })
  })

})()