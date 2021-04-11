<%-- 
    Document   : historyDetail
    Created on : Feb 18, 2021, 11:20:30 PM
    Author     : tienx
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
        <title>History detail Page</title>            
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <link href="css/now-ui-kit.css" rel="stylesheet" />
        
    </head>
    <body class="landing-page sidebar-collapse">
        
        <nav class="navbar navbar-expand-lg bg-info">
            <div class="container">
                <div class="navbar-translate">
                    <a class="navbar-brand" href="index.jsp">Quiz Online</a>
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
                        <li class="nav-item">
                            <a class="nav-link" href="SearchHistoryController">
                                <p>History</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="LogoutController">
                                <p>Logout</p>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- end nav-->
        
        <div class="section section-tabs">
            <div class="container">
                
                
                <div class="row">
                    <div class="col-md-8 ml-auto mr-auto text-center">
                        <h2 class="title">Quiz - Detail</h2>
                        <h3>Number of correct: ${requestScope.NUMOFCORRECT}</h3>
                        <h3>Point: ${requestScope.POINT}</h3>
                    </div>
                </div>                                                
                                   
                    
                <c:forEach items="${requestScope.LISTHISTORYDETAIL}" var="dto" varStatus="counter">
                    <div >                       
                            
                        <h3>${dto.questionContent}</h3>
                        <c:forEach items="${dto.answerContent}" var="dtoAnswer">
                            <div class="form-check form-check-radio">
                                <label class="form-check-label">
                                    <input class="form-check-input" type="radio" name="${dto.questionID}" id="exampleRadios${counter.count}" 
                                           value="${dtoAnswer}" <c:if test="${dtoAnswer eq dto.answer}">checked</c:if>  disabled>
                                    <span class="form-check-sign"></span>
                                    ${dtoAnswer}
                                </label>
                            </div>
                        </c:forEach>     
                            <div>
                                <c:if test="${dto.result}">
                                    <label style="color: green;">Correct </label>
                                </c:if>
                                <c:if test="${!dto.result}">
                                    <label style="color: red;">Correct answer: ${dto.answerCorrect}</label>
                                </c:if>  
                                
                            </div>
                        
                    </div>
                                </br></br>
                </c:forEach>
                
                
                
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
                       
        
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="js/now-ui-kit.js"></script>
    </body>
</html>
