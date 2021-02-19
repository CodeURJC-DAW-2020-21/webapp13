(function(){
  'use strict';

  Charts.init()

  var earnings = []
  var prev = []
  var backgroundColor = []

  // Create a date range for the last 7 days
  var start = moment().subtract(6, 'days').format('YYYY-MM-DD') // 7 days ago
  var end = moment().format('YYYY-MM-DD') // today
  var range = moment.range(start, end)

  // Create the earnings graph data
  // Iterate the date range and assign a random ($) earnings value for each day
  range.by('days', function(m) {
    earnings.push({
      y: Math.floor(Math.random() * 300) + 30,
      x: m.toDate()
    })
    prev.push({
      y: Math.floor(Math.random() * 300) + 10,
      x: m.toDate()
    })

    if (m.startOf('day').isSame(moment().startOf('day'))) {
      backgroundColor.push(settings.colors.accent[500])
    }
    else {
      backgroundColor.push(settings.colors.primary[500])
    }
  })

  var Earnings = function(id, type = 'roundedBar', options = {}) {
    options = Chart.helpers.merge({
      barRoundness: 1.2,
      legend: {
        display: true,
        position: "bottom",
        labels: {
          usePointStyle: true,
          padding: 16
        }
      },
      scales: {
        yAxes: [{
          ticks: {
            callback: function(a) {
              if (!(a % 10))
                return "$" + a
            }
          }
        }],
        xAxes: [{
          offset: true,
          ticks: {
            padding: 10
          },
          maxBarThickness: 20,
          gridLines: {
            display: false
          },
          type: 'time',
          time: {
            unit: 'day'
          }
        }]
      },
      tooltips: {
        callbacks: {
          label: function(a, e) {
            var t = e.datasets[a.datasetIndex].label || "",
                o = a.yLabel,
                r = "";
            return 1 < e.datasets.length && (r += '<span class="popover-body-label mr-auto">' + t + "</span>"), r += '<span class="popover-body-value">$' + o + "</span>"
          }
        }
      }
    }, options)

    var data = {
      datasets: [{
        label: "Previous Week",
        data: prev,
        backgroundColor: settings.colors.gray[300],
        borderColor: settings.colors.gray[50]
      }, {
        label: "Last Week",
        data: earnings,
        backgroundColor: backgroundColor,
        borderColor: settings.colors.primary[50]
      }, {
        label: "Today",
        data: [],
        backgroundColor: settings.colors.accent[500],
        borderColor: settings.colors.accent[50]
      }]
    }

    Charts.create(id, type, options, data)
  }

  // Create Chart
  Earnings('#earningsChart')

})()