$(function(){
    $(".sidebarItem>a").click(function(){
        $(".sidebarItem").removeClass("active")
        $(this).parent().addClass("active")
        $(this).siblings().toggleClass("active")
    })


    $(".sidebarsubItem>ul>li").click(function(){
       $(this).siblings().removeClass("active")
        $(this).addClass("active")
    })

    $("#teamSideBarItem>div li").click(function(){

        $(".mybodybox").removeClass("active")
        $("#teamBody").addClass("active")

    })

    $("#taskSideBarItem>div li").click(function(){
         $(".mybodybox").removeClass("active")
         $("#taskBody").addClass("active")
    })

    $("#userSideBarItem>div li").click(function(){
        $(".mybodybox").removeClass("active")
        $("#usersBody").addClass("active")
    })
})