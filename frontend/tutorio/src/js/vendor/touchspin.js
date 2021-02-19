(function(){
  'use strict';

  $('[data-toggle="touch-spin"]').each(function() {
    $(this).TouchSpin({
      buttondown_class: 'form-control',
      buttonup_class: 'form-control'
    })
  })

})()