/**
 * 
 */
	$(document).ready(function(){
		var type = "mood";
		var currentPage = 1;
		getDataAndDisplay(type,currentPage);
	});

	/**
	 * 获取所有文章列表
	 * @param obj
	 */
    function getArticle(obj){
    	var type = $(obj).prop("name");
    	var currentPage = $(obj).prop("id");
    	getDataAndDisplay(type,currentPage);
    }
    /**
     *获取具体类型的数据并在前台页面展示出来
     *
      */
    function getDataAndDisplay(type,currentPage){
    	if(type != "" && type != null){
    		if($("#articles").length == 0){
    			
    		}
    		$.post("ajax/GetArticle!getArticles?articleType=" +type+"&currentPage="+currentPage,null,function(rep){    
    			//先清空原有内容
    			$("#articles").empty();
             	$.each(rep.articles, function(key, value) {
             		var image = value.image == "" ? "image/default.png" : value.image;
             		if($("#"+value.id+"").length <= 0){
             			$("#articles").append("<li class="+"\"list-group-item\""+" id=\""+value.id+"\" style="+"\"background-color:#dff0d8\">"+
    	                        "<div"+ " class="+"\"article\""+">"+
    	                            "<div"+" class="+"\"picture\""+">"+
    	                                "<img"+" src=\""+image+"\" style="+"\"width:"+" 40px;"+"height:"+" 40px;border-radius: 20px\">"+
    	                            "</div>"+
    	                            "<div"+" class="+"\"title\""+">"+
    	                                "<nobr>"+
    	                                    "<h4>"+
    	                                        "<a" +" href=\"GetArticleDetail?articleType="+type+"&article_id="+value.id+"\">"+key+"</a>"+
    	                                    "</h4>"+
    	                                "</nobr>"+
    	                            "</div>"+
    	                            "<div"+" class="+"\"info\""+">"+
    	                                "<div"+" class="+"\"info-left\""+">"+
    	                                    "<h5"+" style="+"\"text-align: left;margin-left: 55px\""+">"+" <span>本文由：</span><strong>"+value.author_username+"</strong><span>"+" 发表于："+value.writeTime+"</span>"+"</h5>"+
    	                                "</div>"+
    	                                "<div"+" class="+"\"info-right\""+">"+
    	                                    "<h5"+" style="+"\"text-align: right;margin-right: 30px\""+">"+"<span>回复:"+"</span>"+"<span>("+value.replyCount+")</span></h5>"+
    	                                "</div>"+
    	                            "</div>"+
    	                        "</div>"+
    	                    "</li>");
                 		}
             		});
             	var articlesCount = null;
             	var pageCount = null;
             	switch(type){
	    			case "mood" : articlesCount = rep.data.mood_articlesCount;
	    						  pageCount = rep.data.mood_pageCount;
	    						  break;
	    			case "baike" : articlesCount = rep.data.baike_articlesCount;
	    						  pageCount = rep.data.baike_pageCount;
	    						  break;
	    			case "life" : articlesCount = rep.data.life_articlesCount;
	    						  pageCount = rep.data.life_pageCount;
	    						  break;
	    			case "yuan" : articlesCount = rep.data.yuan_articlesCount;
	    						  pageCount = rep.data.yuan_pageCount;
	    						  break;
	    			case "technology" : articlesCount = rep.data.technology_articlesCount;
	    						  pageCount = rep.data.technology_pageCount;
	    						  break;
	    			case "experience" : articlesCount = rep.data.experience_articlesCount;
	    						  pageCount = rep.data.experience_pageCount;
	    						  break;
	    			default :articlesCount = rep.data.mood_articlesCount;
	    						  pageCount = rep.data.mood_pageCount;
	    						  break;
             	}
             	//先清空原有内容
             	$("#pages").empty();
             	$("#pages").append("<li><a href=\"#\""+">&laquo;</a></li>");
             	for(var i =1;i<=pageCount;i++){
             		$("#pages").append("<li><a href=\"javascript:void(0)\" name=\""+type+"\" id=\""+i+"\" onclick=\"getArticle(this)\">"+i+"</a></li>");
             	}
             	$("#pages").append("<li><a href=\"#\""+">&raquo;</a></li>");
             	$("#articlesCount").text(articlesCount);
             	$("#pageCount").text(pageCount);
//             	$("#pages").append("<p>总条数:<span>"+articlesCount+"</span>条 "+" 当前页数：<span>"+pageCount+"</span> 页</p>");
            });   
    	}
    }