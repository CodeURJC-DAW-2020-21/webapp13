!function (t) {
    var n = {};

    function e(r) {
        if (n[r]) return n[r].exports;
        var o = n[r] = {i: r, l: !1, exports: {}};
        return t[r].call(o.exports, o, o.exports, e), o.l = !0, o.exports
    }

    e.m = t, e.c = n, e.d = function (t, n, r) {
        e.o(t, n) || Object.defineProperty(t, n, {enumerable: !0, get: r})
    }, e.r = function (t) {
        "undefined" != typeof Symbol && Symbol.toStringTag && Object.defineProperty(t, Symbol.toStringTag, {value: "Module"}), Object.defineProperty(t, "__esModule", {value: !0})
    }, e.t = function (t, n) {
        if (1 & n && (t = e(t)), 8 & n) return t;
        if (4 & n && "object" == typeof t && t && t.__esModule) return t;
        var r = Object.create(null);
        if (e.r(r), Object.defineProperty(r, "default", {
            enumerable: !0,
            value: t
        }), 2 & n && "string" != typeof t) for (var o in t) e.d(r, o, function (n) {
            return t[n]
        }.bind(null, o));
        return r
    }, e.n = function (t) {
        var n = t && t.__esModule ? function () {
            return t.default
        } : function () {
            return t
        };
        return e.d(n, "a", n), n
    }, e.o = function (t, n) {
        return Object.prototype.hasOwnProperty.call(t, n)
    }, e.p = "/", e(e.s = 453)
}({
    1: function (t, n) {
        var e = t.exports = "undefined" != typeof window && window.Math == Math ? window : "undefined" != typeof self && self.Math == Math ? self : Function("return this")();
        "number" == typeof __g && (__g = e)
    }, 10: function (t, n) {
        function e(t) {
            return (e = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function (t) {
                return typeof t
            } : function (t) {
                return t && "function" == typeof Symbol && t.constructor === Symbol && t !== Symbol.prototype ? "symbol" : typeof t
            })(t)
        }

        function r(n) {
            return "function" == typeof Symbol && "symbol" === e(Symbol.iterator) ? t.exports = r = function (t) {
                return e(t)
            } : t.exports = r = function (t) {
                return t && "function" == typeof Symbol && t.constructor === Symbol && t !== Symbol.prototype ? "symbol" : e(t)
            }, r(n)
        }

        t.exports = r
    }, 11: function (t, n, e) {
        var r = e(1), o = e(7), i = e(13), a = e(18)("src"), c = e(45), s = ("" + c).split("toString");
        e(14).inspectSource = function (t) {
            return c.call(t)
        }, (t.exports = function (t, n, e, c) {
            var u = "function" == typeof e;
            u && (i(e, "name") || o(e, "name", n)), t[n] !== e && (u && (i(e, a) || o(e, a, t[n] ? "" + t[n] : s.join(String(n)))), t === r ? t[n] = e : c ? t[n] ? t[n] = e : o(t, n, e) : (delete t[n], o(t, n, e)))
        })(Function.prototype, "toString", function () {
            return "function" == typeof this && this[a] || c.call(this)
        })
    }, 12: function (t, n, e) {
        var r = e(1), o = e(14), i = e(7), a = e(11), c = e(17), s = function (t, n, e) {
            var u, l, f, p, d = t & s.F, y = t & s.G, v = t & s.S, h = t & s.P, g = t & s.B,
                b = y ? r : v ? r[n] || (r[n] = {}) : (r[n] || {}).prototype, m = y ? o : o[n] || (o[n] = {}),
                x = m.prototype || (m.prototype = {});
            for (u in y && (e = n), e) f = ((l = !d && b && void 0 !== b[u]) ? b : e)[u], p = g && l ? c(f, r) : h && "function" == typeof f ? c(Function.call, f) : f, b && a(b, u, f, t & s.U), m[u] != f && i(m, u, p), h && x[u] != f && (x[u] = f)
        };
        r.core = o, s.F = 1, s.G = 2, s.S = 4, s.P = 8, s.B = 16, s.W = 32, s.U = 64, s.R = 128, t.exports = s
    }, 13: function (t, n) {
        var e = {}.hasOwnProperty;
        t.exports = function (t, n) {
            return e.call(t, n)
        }
    }, 14: function (t, n) {
        var e = t.exports = {version: "2.6.4"};
        "number" == typeof __e && (__e = e)
    }, 15: function (t, n, e) {
        for (var r = e(32), o = e(30), i = e(11), a = e(1), c = e(7), s = e(23), u = e(2), l = u("iterator"), f = u("toStringTag"), p = s.Array, d = {
            CSSRuleList: !0,
            CSSStyleDeclaration: !1,
            CSSValueList: !1,
            ClientRectList: !1,
            DOMRectList: !1,
            DOMStringList: !1,
            DOMTokenList: !0,
            DataTransferItemList: !1,
            FileList: !1,
            HTMLAllCollection: !1,
            HTMLCollection: !1,
            HTMLFormElement: !1,
            HTMLSelectElement: !1,
            MediaList: !0,
            MimeTypeArray: !1,
            NamedNodeMap: !1,
            NodeList: !0,
            PaintRequestList: !1,
            Plugin: !1,
            PluginArray: !1,
            SVGLengthList: !1,
            SVGNumberList: !1,
            SVGPathSegList: !1,
            SVGPointList: !1,
            SVGStringList: !1,
            SVGTransformList: !1,
            SourceBufferList: !1,
            StyleSheetList: !0,
            TextTrackCueList: !1,
            TextTrackList: !1,
            TouchList: !1
        }, y = o(d), v = 0; v < y.length; v++) {
            var h, g = y[v], b = d[g], m = a[g], x = m && m.prototype;
            if (x && (x[l] || c(x, l, p), x[f] || c(x, f, g), s[g] = p, b)) for (h in r) x[h] || i(x, h, r[h], !0)
        }
    }, 17: function (t, n, e) {
        var r = e(29);
        t.exports = function (t, n, e) {
            if (r(t), void 0 === n) return t;
            switch (e) {
                case 1:
                    return function (e) {
                        return t.call(n, e)
                    };
                case 2:
                    return function (e, r) {
                        return t.call(n, e, r)
                    };
                case 3:
                    return function (e, r, o) {
                        return t.call(n, e, r, o)
                    }
            }
            return function () {
                return t.apply(n, arguments)
            }
        }
    }, 18: function (t, n) {
        var e = 0, r = Math.random();
        t.exports = function (t) {
            return "Symbol(".concat(void 0 === t ? "" : t, ")_", (++e + r).toString(36))
        }
    }, 19: function (t, n, e) {
        var r = e(28), o = Math.min;
        t.exports = function (t) {
            return t > 0 ? o(r(t), 9007199254740991) : 0
        }
    }, 2: function (t, n, e) {
        var r = e(20)("wks"), o = e(18), i = e(1).Symbol, a = "function" == typeof i;
        (t.exports = function (t) {
            return r[t] || (r[t] = a && i[t] || (a ? i : o)("Symbol." + t))
        }).store = r
    }, 20: function (t, n, e) {
        var r = e(14), o = e(1), i = o["__core-js_shared__"] || (o["__core-js_shared__"] = {});
        (t.exports = function (t, n) {
            return i[t] || (i[t] = void 0 !== n ? n : {})
        })("versions", []).push({
            version: r.version,
            mode: e(26) ? "pure" : "global",
            copyright: "© 2019 Denis Pushkarev (zloirock.ru)"
        })
    }, 21: function (t, n) {
        t.exports = function (t) {
            if (null == t) throw TypeError("Can't call method on  " + t);
            return t
        }
    }, 22: function (t, n) {
        var e = {}.toString;
        t.exports = function (t) {
            return e.call(t).slice(8, -1)
        }
    }, 23: function (t, n) {
        t.exports = {}
    }, 24: function (t, n, e) {
        var r = e(43), o = e(21);
        t.exports = function (t) {
            return r(o(t))
        }
    }, 25: function (t, n, e) {
        var r = e(21);
        t.exports = function (t) {
            return Object(r(t))
        }
    }, 26: function (t, n) {
        t.exports = !1
    }, 27: function (t, n) {
        t.exports = function (t, n) {
            return {enumerable: !(1 & t), configurable: !(2 & t), writable: !(4 & t), value: n}
        }
    }, 28: function (t, n) {
        var e = Math.ceil, r = Math.floor;
        t.exports = function (t) {
            return isNaN(t = +t) ? 0 : (t > 0 ? r : e)(t)
        }
    }, 29: function (t, n) {
        t.exports = function (t) {
            if ("function" != typeof t) throw TypeError(t + " is not a function!");
            return t
        }
    }, 30: function (t, n, e) {
        var r = e(51), o = e(38);
        t.exports = Object.keys || function (t) {
            return r(t, o)
        }
    }, 31: function (t, n, e) {
        var r = e(4), o = e(1).document, i = r(o) && r(o.createElement);
        t.exports = function (t) {
            return i ? o.createElement(t) : {}
        }
    }, 32: function (t, n, e) {
        "use strict";
        var r = e(40), o = e(54), i = e(23), a = e(24);
        t.exports = e(49)(Array, "Array", function (t, n) {
            this._t = a(t), this._i = 0, this._k = n
        }, function () {
            var t = this._t, n = this._k, e = this._i++;
            return !t || e >= t.length ? (this._t = void 0, o(1)) : o(0, "keys" == n ? e : "values" == n ? t[e] : [e, t[e]])
        }, "values"), i.Arguments = i.Array, r("keys"), r("values"), r("entries")
    }, 33: function (t, n, e) {
        var r = e(4);
        t.exports = function (t, n) {
            if (!r(t)) return t;
            var e, o;
            if (n && "function" == typeof (e = t.toString) && !r(o = e.call(t))) return o;
            if ("function" == typeof (e = t.valueOf) && !r(o = e.call(t))) return o;
            if (!n && "function" == typeof (e = t.toString) && !r(o = e.call(t))) return o;
            throw TypeError("Can't convert object to primitive value")
        }
    }, 34: function (t, n, e) {
        var r = e(9).f, o = e(13), i = e(2)("toStringTag");
        t.exports = function (t, n, e) {
            t && !o(t = e ? t : t.prototype, i) && r(t, i, {configurable: !0, value: n})
        }
    }, 36: function (t, n, e) {
        var r = e(20)("keys"), o = e(18);
        t.exports = function (t) {
            return r[t] || (r[t] = o(t))
        }
    }, 38: function (t, n) {
        t.exports = "constructor,hasOwnProperty,isPrototypeOf,propertyIsEnumerable,toLocaleString,toString,valueOf".split(",")
    }, 39: function (t, n, e) {
        t.exports = !e(6) && !e(8)(function () {
            return 7 != Object.defineProperty(e(31)("div"), "a", {
                get: function () {
                    return 7
                }
            }).a
        })
    }, 4: function (t, n) {
        t.exports = function (t) {
            return "object" == typeof t ? null !== t : "function" == typeof t
        }
    }, 40: function (t, n, e) {
        var r = e(2)("unscopables"), o = Array.prototype;
        null == o[r] && e(7)(o, r, {}), t.exports = function (t) {
            o[r][t] = !0
        }
    }, 43: function (t, n, e) {
        var r = e(22);
        t.exports = Object("z").propertyIsEnumerable(0) ? Object : function (t) {
            return "String" == r(t) ? t.split("") : Object(t)
        }
    }, 44: function (t, n, e) {
        var r = e(5), o = e(65), i = e(38), a = e(36)("IE_PROTO"), c = function () {
        }, s = function () {
            var t, n = e(31)("iframe"), r = i.length;
            for (n.style.display = "none", e(50).appendChild(n), n.src = "javascript:", (t = n.contentWindow.document).open(), t.write("<script>document.F=Object<\/script>"), t.close(), s = t.F; r--;) delete s.prototype[i[r]];
            return s()
        };
        t.exports = Object.create || function (t, n) {
            var e;
            return null !== t ? (c.prototype = r(t), e = new c, c.prototype = null, e[a] = t) : e = s(), void 0 === n ? e : o(e, n)
        }
    }, 45: function (t, n, e) {
        t.exports = e(20)("native-function-to-string", Function.toString)
    }, 453: function (t, n, e) {
        t.exports = e(454)
    }, 454: function (t, n, e) {
        "use strict";
        e.r(n);
        e(455)
    }, 455: function (t, n, e) {
        e(15);
        var r = e(10), o = function t(n, e) {
            for (var o in e) "object" != r(e[o]) ? n[o] = e[o] : t(n[o], e[o])
        }, i = function (t) {
            var n = t.data("add"), e = $(t.data("target")).data("chart");
            if (t.is(":checked")) {
                !function t(n, e) {
                    for (var r in e) Array.isArray(e[r]) ? e[r].forEach(function (t) {
                        n[r].push(t)
                    }) : t(n[r], e[r])
                }(e, n)
            } else {
                !function t(n, e) {
                    for (var r in e) Array.isArray(e[r]) ? e[r].forEach(function (t) {
                        n[r].pop()
                    }) : t(n[r], e[r])
                }(e, n)
            }
            e.update()
        }, a = function (t) {
            var n = t.data("update"), e = $(t.data("target")).data("chart");
            if (o(e, n), void 0 !== t.data("prefix") || void 0 !== t.data("suffix")) {
                var r = t.data("prefix") ? t.data("prefix") : "", i = t.data("suffix") ? t.data("suffix") : "";
                void 0 !== e.options.scales && (e.options.scales.yAxes[0].ticks.callback = function (t) {
                    if (!(t % 10)) return r + t + i
                }), e.options.tooltips.callbacks.label = function (t, n) {
                    var e = n.datasets[t.datasetIndex].label || "", o = t.yLabel || n.datasets[0].data[t.index], a = "";
                    return 1 < n.datasets.length && (a += '<span class="popover-body-label mr-auto">' + e + "</span>"), a + '<span class="popover-body-value">' + r + o + i + "</span>"
                }
            }
            e.update()
        }, c = {
            responsive: !0,
            maintainAspectRatio: !1,
            defaultColor: "dark" == settings.charts.colorScheme ? settings.colors.gray[700] : settings.colors.gray[600],
            defaultFontColor: "dark" == settings.charts.colorScheme ? settings.colors.gray[700] : settings.colors.gray[600],
            defaultFontFamily: settings.fonts.base,
            defaultFontSize: 13,
            layout: {padding: 0},
            legend: {display: !1, position: "bottom", labels: {usePointStyle: !0, padding: 16}},
            elements: {
                point: {radius: 0, backgroundColor: settings.colors.primary[500]},
                line: {
                    tension: .4,
                    borderWidth: 3,
                    borderColor: settings.colors.primary[500],
                    backgroundColor: settings.colors.transparent,
                    borderCapStyle: "rounded"
                },
                rectangle: {backgroundColor: settings.colors.primary[500]},
                arc: {
                    backgroundColor: settings.colors.primary[500],
                    borderColor: "dark" == settings.charts.colorScheme ? settings.colors.gray[800] : settings.colors.white,
                    borderWidth: 4
                }
            },
            tooltips: {
                enabled: !1, mode: "index", intersect: !1, custom: function (t) {
                    var n = $("#chart-tooltip");
                    if (n.length || (n = $('<div id="chart-tooltip" class="popover bs-popover-top" role="tooltip"></div>'), $("body").append(n)), 0 !== t.opacity) {
                        if (t.body) {
                            var e = t.title || [], r = t.body.map(function (t) {
                                return t.lines
                            }), o = "";
                            o += '<div class="arrow"></div>', e.forEach(function (t) {
                                o += '<h3 class="popover-header text-center">' + t + "</h3>"
                            }), r.forEach(function (n, e) {
                                var i = '<span class="popover-body-indicator" style="background-color: ' + t.labelColors[e].backgroundColor + '"></span>',
                                    a = 1 < r.length ? "justify-content-left" : "justify-content-center";
                                o += '<div class="popover-body d-flex align-items-center ' + a + '">' + i + n + "</div>"
                            }), n.html(o)
                        }
                        var i = $(this._chart.canvas), a = (i.outerWidth(), i.outerHeight(), i.offset().top),
                            c = i.offset().left, s = n.outerWidth(), u = n.outerHeight(), l = a + t.caretY - u - 16,
                            f = c + t.caretX - s / 2;
                        n.css({top: l + "px", left: f + "px", display: "block"})
                    } else n.css("display", "none")
                }, callbacks: {
                    label: function (t, n) {
                        var e = n.datasets[t.datasetIndex].label || "", r = t.yLabel, o = "";
                        return 1 < n.datasets.length && (o += '<span class="popover-body-label mr-auto">' + e + "</span>"), o + '<span class="popover-body-value">' + r + "</span>"
                    }
                }
            }
        }, s = {
            cutoutPercentage: 83, tooltips: {
                callbacks: {
                    title: function (t, n) {
                        return n.labels[t[0].index]
                    }, label: function (t, n) {
                        return "" + '<span class="popover-body-value">' + n.datasets[0].data[t.index] + "</span>"
                    }
                }
            }, legendCallback: function (t) {
                var n = t.data, e = "";
                return n.labels.forEach(function (t, r) {
                    var o = n.datasets[0].backgroundColor[r];
                    e += '<span class="chart-legend-item">', e += '<i class="chart-legend-indicator" style="background-color: ' + o + '"></i>', e += t, e += "</span>"
                }), e
            }
        }, u = {
            settings: settings, init: function () {
                o(Chart, {
                    defaults: {
                        global: c,
                        doughnut: s
                    }
                }), Chart.scaleService.updateScaleDefaults("linear", {
                    gridLines: {
                        borderDash: [2],
                        borderDashOffset: [2],
                        color: "dark" == settings.charts.colorScheme ? settings.colors.gray[900] : settings.colors.gray[100],
                        drawBorder: !1,
                        drawTicks: !1,
                        lineWidth: 0,
                        zeroLineWidth: 0,
                        zeroLineColor: "dark" == settings.charts.colorScheme ? settings.colors.gray[900] : settings.colors.gray[100],
                        zeroLineBorderDash: [2],
                        zeroLineBorderDashOffset: [2]
                    }, ticks: {
                        beginAtZero: !0, padding: 10, callback: function (t) {
                            if (!(t % 10)) return t
                        }
                    }
                }), Chart.scaleService.updateScaleDefaults("category", {
                    gridLines: {
                        drawBorder: !1,
                        drawOnChartArea: !1,
                        drawTicks: !1
                    }, ticks: {padding: 20}, maxBarThickness: 10
                }), $('[data-toggle="chart"]').on({
                    change: function () {
                        var t = $(this);
                        t.is("[data-add]") && i(t)
                    }, click: function () {
                        var t = $(this);
                        t.is("[data-update]") && a(t)
                    }
                })
            }, add: i, update: a, create: function (t) {
                var n = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : "line",
                    e = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : {},
                    r = arguments.length > 3 && void 0 !== arguments[3] ? arguments[3] : {}, o = $(t),
                    i = new Chart(o, {type: n, options: e, data: r});
                o.data("chart", i), o.data("chart-legend") && (document.querySelector(o.data("chart-legend")).innerHTML = i.generateLegend())
            }
        };
        void 0 !== window && (window.Charts = u)
    }, 49: function (t, n, e) {
        "use strict";
        var r = e(26), o = e(12), i = e(11), a = e(7), c = e(23), s = e(64), u = e(34), l = e(66), f = e(2)("iterator"),
            p = !([].keys && "next" in [].keys()), d = function () {
                return this
            };
        t.exports = function (t, n, e, y, v, h, g) {
            s(e, n, y);
            var b, m, x, S = function (t) {
                    if (!p && t in w) return w[t];
                    switch (t) {
                        case"keys":
                        case"values":
                            return function () {
                                return new e(this, t)
                            }
                    }
                    return function () {
                        return new e(this, t)
                    }
                }, k = n + " Iterator", O = "values" == v, L = !1, w = t.prototype,
                _ = w[f] || w["@@iterator"] || v && w[v], j = _ || S(v), C = v ? O ? S("entries") : j : void 0,
                T = "Array" == n && w.entries || _;
            if (T && (x = l(T.call(new t))) !== Object.prototype && x.next && (u(x, k, !0), r || "function" == typeof x[f] || a(x, f, d)), O && _ && "values" !== _.name && (L = !0, j = function () {
                return _.call(this)
            }), r && !g || !p && !L && w[f] || a(w, f, j), c[n] = j, c[k] = d, v) if (b = {
                values: O ? j : S("values"),
                keys: h ? j : S("keys"),
                entries: C
            }, g) for (m in b) m in w || i(w, m, b[m]); else o(o.P + o.F * (p || L), n, b);
            return b
        }
    }, 5: function (t, n, e) {
        var r = e(4);
        t.exports = function (t) {
            if (!r(t)) throw TypeError(t + " is not an object!");
            return t
        }
    }, 50: function (t, n, e) {
        var r = e(1).document;
        t.exports = r && r.documentElement
    }, 51: function (t, n, e) {
        var r = e(13), o = e(24), i = e(57)(!1), a = e(36)("IE_PROTO");
        t.exports = function (t, n) {
            var e, c = o(t), s = 0, u = [];
            for (e in c) e != a && r(c, e) && u.push(e);
            for (; n.length > s;) r(c, e = n[s++]) && (~i(u, e) || u.push(e));
            return u
        }
    }, 53: function (t, n, e) {
        var r = e(28), o = Math.max, i = Math.min;
        t.exports = function (t, n) {
            return (t = r(t)) < 0 ? o(t + n, 0) : i(t, n)
        }
    }, 54: function (t, n) {
        t.exports = function (t, n) {
            return {value: n, done: !!t}
        }
    }, 57: function (t, n, e) {
        var r = e(24), o = e(19), i = e(53);
        t.exports = function (t) {
            return function (n, e, a) {
                var c, s = r(n), u = o(s.length), l = i(a, u);
                if (t && e != e) {
                    for (; u > l;) if ((c = s[l++]) != c) return !0
                } else for (; u > l; l++) if ((t || l in s) && s[l] === e) return t || l || 0;
                return !t && -1
            }
        }
    }, 6: function (t, n, e) {
        t.exports = !e(8)(function () {
            return 7 != Object.defineProperty({}, "a", {
                get: function () {
                    return 7
                }
            }).a
        })
    }, 64: function (t, n, e) {
        "use strict";
        var r = e(44), o = e(27), i = e(34), a = {};
        e(7)(a, e(2)("iterator"), function () {
            return this
        }), t.exports = function (t, n, e) {
            t.prototype = r(a, {next: o(1, e)}), i(t, n + " Iterator")
        }
    }, 65: function (t, n, e) {
        var r = e(9), o = e(5), i = e(30);
        t.exports = e(6) ? Object.defineProperties : function (t, n) {
            o(t);
            for (var e, a = i(n), c = a.length, s = 0; c > s;) r.f(t, e = a[s++], n[e]);
            return t
        }
    }, 66: function (t, n, e) {
        var r = e(13), o = e(25), i = e(36)("IE_PROTO"), a = Object.prototype;
        t.exports = Object.getPrototypeOf || function (t) {
            return t = o(t), r(t, i) ? t[i] : "function" == typeof t.constructor && t instanceof t.constructor ? t.constructor.prototype : t instanceof Object ? a : null
        }
    }, 7: function (t, n, e) {
        var r = e(9), o = e(27);
        t.exports = e(6) ? function (t, n, e) {
            return r.f(t, n, o(1, e))
        } : function (t, n, e) {
            return t[n] = e, t
        }
    }, 8: function (t, n) {
        t.exports = function (t) {
            try {
                return !!t()
            } catch (t) {
                return !0
            }
        }
    }, 9: function (t, n, e) {
        var r = e(5), o = e(39), i = e(33), a = Object.defineProperty;
        n.f = e(6) ? Object.defineProperty : function (t, n, e) {
            if (r(t), n = i(n, !0), r(e), o) try {
                return a(t, n, e)
            } catch (t) {
            }
            if ("get" in e || "set" in e) throw TypeError("Accessors not supported!");
            return "value" in e && (t[n] = e.value), t
        }
    }
});