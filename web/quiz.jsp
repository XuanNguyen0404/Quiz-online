<%-- 
    Document   : quiz
    Created on : Feb 16, 2021, 2:46:42 PM
    Author     : tienx
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
        <title>Quiz Page</title>            
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <link href="css/now-ui-kit.css" rel="stylesheet" />
        
    </head>
    <body class="landing-page sidebar-collapse">
        
        <nav class="navbar navbar-expand-lg bg-info">
            <div class="container">
                <div class="navbar-translate">
                    <a class="navbar-brand" href="#">Quiz Online</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#example-navbar-info" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-bar bar1"></span>
                        <span class="navbar-toggler-bar bar2"></span>
                        <span class="navbar-toggler-bar bar3"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse" id="example-navbar-info">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active">
                            <a class="nav-link" disabled>
                                <p>Welcome , ${sessionScope.USERNAME}</p>
                            </a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" disabled>
                                Time: <span id="demo" style="color:#FF0000;font-size:15px"></span></div>
                            </a>
                        </li>  
                        
                            <form action="FinishQuizController" method="POST" name="FormFinish" id="FormFinish">
                                <button class="btn btn-info" type="submit">Finish</button>
                            </form>
                        
                        
                        
                    </ul>
                </div>
            </div>
        </nav>
        <!-- end nav-->
        
        <div class="section section-tabs">
            <div class="container">
                
                
                <div class="row">
                    <div class="col-md-8 ml-auto mr-auto text-center">
                        <h2 class="title">Quiz online</h2>
                    </div>
                </div>                                                                              
                               
                    <div >
                        <form action="ChooseAnswerController" method="POST" name="MCQuestion" id="MCQuestion" >
                            <input type="hidden" name="questionID" value="${sessionScope.QUESTIONINQUIZ.questionID}"/>
                            <h3>Q${sessionScope.INDEXQUIZ}: ${sessionScope.QUESTIONINQUIZ.questionContent}</h3>
                            <c:forEach items="${sessionScope.QUESTIONINQUIZ.answerContent}" var="dtoAnswer">
                            <div class="form-check form-check-radio">
                                <label class="form-check-label">
                                    <input onchange="this.form.submit();" class="form-check-input" type="radio" name="answer" 
                                           <c:if test="${dtoAnswer eq sessionScope.QUESTIONINQUIZ.answer}">checked</c:if> id="exampleRadios" value="${dtoAnswer}">
                                    <span class="form-check-sign"></span>
                                    ${dtoAnswer}
                                </label>
                            </div>
                            </c:forEach>
                        </form>
                        
                    </div>
                                </br></br>
                
                
                <div>
                    <c:if test="${sessionScope.INDEXQUIZ ne 1}">
                        <a class="btn btn-info previous" href="ChangePageQuizController?action=previos">&laquo; Previous</a>
                    </c:if> 
                    <c:if test="${!sessionScope.INDEXISMAX}">
                        <a class="btn btn-info next" href="ChangePageQuizController?action=next" style="float: right;">Next &raquo;</a>
                    </c:if>
                    
                </div></br>
                
                
                
            </div>
        </div>
        
        
        <footer class="footer">
            <div class=" container ">
                <nav>
                    <ul>                       
                        <li>
                            <h5>About Us</h5>
                        </li>
                    </ul>
                </nav>
                <div class="copyright" id="copyright">
                    &copy;
                    Copy right by Xuan             
                </div>
            </div>
        </footer>
                
        <script>
            // Set the date we're counting down to
            var countDownDate = new Date("${sessionScope.TIMEOUT}").getTime();
            
            // Update the count down every 1 second
            var x = setInterval(function() {

                // Get today's date and time
                var now = new Date().getTime();
    
                // Find the distance between now and the count down date
                var distance = countDownDate - now;
    
                // Time calculations for days, hours, minutes and seconds               
                var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                var seconds = Math.floor((distance % (1000 * 60)) / 1000);
    
                // Output the result in an element with id="demo"
                document.getElementById("demo").innerHTML = minutes + "m " + seconds + "s ";
    
                // If the count down is over, write some text 
                if (distance < 0) {
                    clearInterval(x);
                    document.getElementById("demo").innerHTML = "EXPIRED";
                    document.getElementById('FormFinish').submit();
                }
            }, 1000);
        </script>
        
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="js/now-ui-kit.js"></script>
    </body>
</html>
