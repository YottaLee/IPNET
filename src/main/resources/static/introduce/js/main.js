(function ($) {
    var isMobile = false;

    if (Modernizr.mq('only all and (max-width: 1024px)')) {
        isMobile = true;
        $(".carousel .item").css("height", "350px");
        $(".notransition").css("opacity", "1");
        $(".qrcode").css("background", "none");
        $(".vrone").attr("data-toggle", "");
    }else{
        $(".carousel .item").css("height", "700px");
    }
    /*===================================================================================*/
    /*	加载全景
     /*===================================================================================*/
    var frameload = $("#frameload");
    if (frameload.length) {
        var winHeight = 0;
        //获取窗口高度
        if (window.innerHeight) {
            winHeight = window.innerHeight;
        } else if ((document.body) && (document.body.clientHeight)) {
            winHeight = document.body.clientHeight;
        }
        //通过深入Document内部对body进行检测，获取窗口高度
        if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) {
            winHeight = document.documentElement.clientHeight;
        }
        frameload.empty().append("<div style='height: " + winHeight + "px;width: 100%;position: absolute;z-index: 2;'><img src='img/t4.png' class='img-responsive center-block notransition animated fadeInUpNow animation103' style='margin-top: " + winHeight / 2 + "px;padding-left: 20px;padding-right: 20px;'></div> <iframe frameborder='0' width='100%' scrolling='no'style='margin: 0 auto;z-index: 0'height=" + winHeight + " src='../pages/index.jsp'></iframe>");
    }
    // $("#footer").load("footer.html");
    /*===================================================================================*/
    /*	缓慢滑动
     /*===================================================================================*/
    $('.smooth').click(function () {
        $('html, body').animate({
            scrollTop: $($.attr(this, 'href')).offset().top
        }, 500);
        return true;
    });

    // $(window).onscroll = function(){
    //     if(document.getElementById("change").getAttribute("margin-top")>20){
    //         document.getElementById("logo").src="../img/logo.png";
    //     }else{
    //         document.getElementById("logo").src="../img/logo1.png";
    //     }
    // }



    /*===================================================================================*/
    /*	动态分类isotope
     /*===================================================================================*/
    var wrapper = $('.isotopeWrapper');
    if (wrapper.length) {
        var $container = wrapper;
        var $resize = wrapper.attr('id');
        // initialize isotope
        $container.isotope({
            itemSelector: '.isotopeItem',
            resizable: false, // disable normal resizing
            masonry: {
                columnWidth: $container.width() / $resize
            }
        });
        var filter = $('#filter').find('a');
        // var filter =  $('#filter a');
        filter.click(function () {
            filter.removeClass('current');
            $(this).addClass('current');
            var selector = $(this).attr('data-filter');
            $container.isotope({
                filter: selector,
                animationOptions: {
                    duration: 1000,
                    easing: 'easeOutQuart',
                    queue: false
                }
            });
            return false;
        });
        $(window).smartresize(function () {
            $container.isotope({
                // update columnWidth to a percentage of container width
                masonry: {
                    columnWidth: $container.width() / $resize
                }
            });
        });
    }
    /*===================================================================================*/
    /*	nav视差动画skrollr
     /*===================================================================================*/
    var s = skrollr;
    var sActive = false;
    if ($(window).width() > 767) {
        s.init({
            mobileCheck: function () {
                return false;
            }
        });
        sActive = true;
    }
    $(window).on('resize', function () {
        if ($(window).width() < 767 && sActive) {
            s.init().destroy();
            sActive = false;
        }
        else if ($(window).width() > 767) {
            s.init({
                mobileCheck: function () {
                    return false;
                }
            });
            sActive = true;
        }
    });

    /*===================================================================================*/
    /*	 弹出框fancybox fancybox
     /*===================================================================================*/
    //jQuery(".fancybox").fancybox();

    if (isMobile == false) {
        /*===================================================================================*/
        /*	滑动监听scroll menu appear
         /*===================================================================================*/
        var appear = $(".appear");
        appear.appear();
        appear.on("appear", function () {
            var id = $(this).attr("id");
            jQuery('.cd-vertical-nav a').removeClass('active');
            jQuery(".cd-vertical-nav a[href='#" + id + "']").addClass("active");
        });

        var timelineBlocks = $('.cd-timeline-block'),
            offset = 0.8;

        /*===================================================================================*/
        /*	侧边栏
         /*===================================================================================*/
        //hide timeline blocks which are outside the viewport
        hideBlocks(timelineBlocks, offset);

        //on scolling, show/animate timeline blocks when enter the viewport
        $(window).on('scroll', function () {
            (!window.requestAnimationFrame)
                ? setTimeout(function () {
                showBlocks(timelineBlocks, offset);
            }, 100)
                : window.requestAnimationFrame(function () {
                showBlocks(timelineBlocks, offset);
            });

        });

        function hideBlocks(blocks, offset) {
            blocks.each(function () {
                ( $(this).offset().top > $(window).scrollTop() + $(window).height() * offset ) && $(this).find('.cd-timeline-img, .cd-timeline-content').addClass('is-hidden');
            });
        }

        function showBlocks(blocks, offset) {
            blocks.each(function () {
                ( $(this).offset().top <= $(window).scrollTop() + $(window).height() * offset && $(this).find('.cd-timeline-img').hasClass('is-hidden') ) && $(this).find('.cd-timeline-img, .cd-timeline-content').removeClass('is-hidden').addClass('bounce-in');
            });
        }

        /*===================================================================================*/
        /*	视差parallax
         /*===================================================================================*/
        $(window).stellar({
            responsive: true,
            scrollProperty: 'scroll',
            parallaxElements: false,
            horizontalScrolling: false,
            horizontalOffset: 0,
            verticalOffset: 0
        });
        /*===================================================================================*/
        /*	动画效果
         /*===================================================================================*/
        jQuery('.animated').appear();

        jQuery(document.body).on('appear', '.fade', function () {
            jQuery(this).each(function () {
                jQuery(this).addClass('anim-fade')
            });
        });

        jQuery(document.body).on('appear', '.fadeInUpNow', function () {
            jQuery(this).each(function () {
                jQuery(this).addClass('fadeInUp')
            });
        });
        jQuery(document.body).on('appear', '.fadeInDownNow', function () {
            jQuery(this).each(function () {
                jQuery(this).addClass('fadeInDown')
            });
        });
        jQuery(document.body).on('appear', '.fadeInLeftNow', function () {
            jQuery(this).each(function () {
                jQuery(this).addClass('fadeInLeft')
            });
        });
        jQuery(document.body).on('appear', '.fadeInRightNow', function () {
            jQuery(this).each(function () {
                jQuery(this).addClass('fadeInRight')
            });
        });

        jQuery(document.body).on('appear', '.fadeInUpBigNow', function () {
            jQuery(this).each(function () {
                jQuery(this).addClass('fadeInUpBig')
            });
        });
        jQuery(document.body).on('appear', '.fadeInDownBigNow', function () {
            jQuery(this).each(function () {
                jQuery(this).addClass('fadeInDownBig')
            });
        });
        jQuery(document.body).on('appear', '.fadeInLeftBigNow', function () {
            jQuery(this).each(function () {
                jQuery(this).addClass('fadeInLeftBig')
            });
        });
        jQuery(document.body).on('appear', '.fadeInRightBigNow', function () {
            jQuery(this).each(function () {
                jQuery(this).addClass('fadeInRightBig')
            });
        });
        jQuery(document.body).on('appear', '.fadeInNow', function () {
            jQuery(this).each(function () {
                jQuery(this).addClass('fadeIn')
            });
        });
        $.force_appear()
    }
    /*===================================================================================*/
    /*	返回顶部
     /*===================================================================================*/
    // !function (a, b, c) {
    //     a.fn.scrollUp = function (b) {
    //         a.data(c.body, "scrollUp") || (a.data(c.body, "scrollUp", !0), a.fn.scrollUp.init(b))
    //     }, a.fn.scrollUp.init = function (d) {
    //         var e = a.fn.scrollUp.settings = a.extend({}, a.fn.scrollUp.defaults, d),
    //             f = e.scrollTitle ? e.scrollTitle : e.scrollText,
    //             g = a("<a/>", {
    //                 id: e.scrollName,
    //                 href: "#header", /*,
    //                  title: f*/
    //                 class: "scrollup"
    //             }).appendTo("body");
    //         e.scrollImg || g.html(e.scrollText), g.css({
    //             display: "none",
    //             zIndex: e.zIndex
    //         }), e.activeOverlay && a("<div/>", {
    //             id: e.scrollName + "-active"
    //         }).css({
    //             position: "absolute",
    //             top: e.scrollDistance + "px",
    //             width: "100%",
    //             borderTop: "1px dotted" + e.activeOverlay,
    //             zIndex: e.zIndex
    //         }).appendTo("body"), scrollEvent = a(b).scroll(function () {
    //             switch (scrollDis = "top" === e.scrollFrom ? e.scrollDistance : a(c).height() - a(b).height() - e.scrollDistance, e.animation) {
    //                 case "fade":
    //                     a(a(b).scrollTop() > scrollDis ? g.fadeIn(e.animationInSpeed) : g.fadeOut(e.animationOutSpeed));
    //                     break;
    //                 case "slide":
    //                     a(a(b).scrollTop() > scrollDis ? g.slideDown(e.animationInSpeed) : g.slideUp(e.animationOutSpeed));
    //                     break;
    //                 default:
    //                     a(a(b).scrollTop() > scrollDis ? g.show(0) : g.hide(0))
    //             }
    //         }), g.click(function (b) {
    //             b.preventDefault(), a("html, body").animate({
    //                 scrollTop: 0
    //             }, e.scrollSpeed, e.easingType)
    //         })
    //     }, a.fn.scrollUp.defaults = {
    //         scrollName: "scrollUp",
    //         scrollDistance: 300,
    //         scrollFrom: "top",
    //         scrollSpeed: 300,
    //         easingType: "linear",
    //         animation: "fade",
    //         animationInSpeed: 200,
    //         animationOutSpeed: 200,
    //         scrollText: "Scroll to top",
    //         scrollTitle: !1,
    //         scrollImg: !1,
    //         activeOverlay: !1,
    //         zIndex: 2147483647
    //     }, a.fn.scrollUp.destroy = function (d) {
    //         a.removeData(c.body, "scrollUp"), a("#" + a.fn.scrollUp.settings.scrollName).remove(), a("#" + a.fn.scrollUp.settings.scrollName + "-active").remove(), a.fn.jquery.split(".")[1] >= 7 ? a(b).off("scroll", d) : a(b).unbind("scroll", d)
    //     }, a.scrollUp = a.fn.scrollUp
    // }(jQuery, window, document);
    // $(document).ready(function () {
    //     $.scrollUp({
    //         scrollName: "scrollUp", // Element ID
    //         scrollDistance: 300, // Distance from top/bottom before showing element (px)
    //         scrollFrom: "top", // "top" or "bottom"
    //         scrollSpeed: 1000, // Speed back to top (ms)
    //         easingType: "easeInOutCubic", // Scroll to top easing (see http://easings.net/)
    //         animation: "fade", // Fade, slide, none
    //         animationInSpeed: 200, // Animation in speed (ms)
    //         animationOutSpeed: 200, // Animation out speed (ms)
    //         scrollText: "<i class='fa fa-chevron-up'></i>", // Text for element, can contain HTML
    //         scrollTitle: " ", // Set a custom <a> title if required. Defaults to scrollText
    //         scrollImg: 0, // Set true to use image
    //         activeOverlay: 0, // Set CSS color to display scrollUp active point, e.g "#00FFFF"
    //         zIndex: 999999 // Z-Index for the overlay
    //     });
    // });
    /*===================================================================================*/
    /*	VIDEO
     /*===================================================================================*/
    var videodiv = $('#videodiv');
    var videoitem = $('#videoitem');
    var playtext = $('#playtext');
    videoitem.on('waiting', function () {
        playtext.removeClass('fa-play');
        playtext.addClass('fa-spinner fa-pulse');
    });
    videoitem.on('playing', function () {
        videodiv.hide('fast');
    });
    if (videoitem.length) {
        videodiv.click(function () {
            if (videoitem.hasClass('pause')) {
                videoitem.trigger("play");
                videoitem.removeClass('pause');
                videoitem.addClass('play');
                document.getElementById("videoitem").controls = true;
            } else {
                videoitem.trigger("pause");
                videoitem.removeClass('play');
                videoitem.addClass('pause');
            }
        });
        videoitem.click(function () {
            if ($(this).hasClass('pause')) {
                videoitem.trigger("play");
                $(this).removeClass('pause');
                $(this).addClass('play');
                videodiv.hide('fast');
                document.getElementById("videoitem").controls = true;
            } else {
                videoitem.trigger("pause");
                $(this).removeClass('play');
                $(this).addClass('pause');
            }
        });
    }
    /*===================================================================================*/
    /*	OTHER Function
     /*===================================================================================*/
    //底部图标弹框tooltip
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    });
    $(function () {
        $('[data-toggle="popover"]').popover()
    });

})(jQuery);

