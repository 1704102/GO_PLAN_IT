function toggleMenu(){
    if( $(".menuItem").css("display") ==  "none"){
        $(".menu").css("border-radius", "5%");
        $(".menu").css("width", "120px");
        $(".menu").css("padding-bottom", "10px");
        $(".menuItem").css("display", "block");
    }else{
        $(".menu").css("border-radius", "50%");
        $(".menuItem").css("display", "none");
        $(".menu").css("width", "80px");
        $(".menu").css("padding-bottom", "0px");
    }

}