
let movieCnt = 0;

function getMovieList(){

    movieCnt++;

    let paramData = {
        movieChartCnt : movieCnt,
    };
    let param =JSON.stringify(paramData);

    $.ajax({
        type: "POST",
        url: "/movieChart",
        dataType: "json",
        cache:false,
        data : param,
        success:function(result,status){
            //location.href="/movieChart";
            //데이터 그려주기

        },
    });
}
