
let movieCnt = 1;

function getMovieList(){

    var paramData = new Object();

    paramData = {
        movieChartCnt : movieCnt,
        searchMovieTitle : $("#searchWord").val(),
    };

    var param = JSON.stringify(paramData);


    $.ajax({
        type: "POST",
        url: "/movieChart",
        contentType : "application/json",
        dataType: "json",
        cache:false,
        data : param,
        success:function(result,status){
             let movieChartUI = $("#moreUtil");
             if(result.totalPages-1 == result.number){
                $("#movieViewMoreBtn").hide();
             }
             console.log(result.totalPages);
             console.log(result.number);

             movieCnt++;

             result.content.forEach(function(movieChartCnt){
                movieChartUI.append(
                                        '<form class = "movie">' +
                                            '<div>' +
                                                '<div>' +
                                                    '<a href=""><img class="moviePoster" src = '+movieChartCnt.imgUrl+'></a>' +
                                                '</div>' +
                                                '<div>' +
                                                    '<p class="movieTitle">'+movieChartCnt.movieTitle+'</p>' +
                                                    '<p class="openingDay">'+movieChartCnt.premiereDate+'</p>' +
                                                '</div>' +

                                                '<input type ="button" class="movieChartBtn" value="예매하기">' +
                                            '</div>' +
                                        '</form>'
                )
             })


        },
    });
}
