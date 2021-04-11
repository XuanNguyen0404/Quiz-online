<%-- 
    Document   : index
    Created on : Feb 16, 2021, 1:03:45 PM
    Author     : tienx
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
        <title>Main Page</title>            
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
                        <h2 class="title">Student Page</h2>
                    </div>
                </div>
                
                <p style="color: red;">${requestScope.INVALID}</p>

                <c:forEach items="${sessionScope.LISTSUBJECT}" var="dto" varStatus="counter">
                <div class="card">
                    
                    <div class="card-body">
                        <h5 class="card-title">${dto.subjectID} - ${dto.subjectName}</h5>
                        <p class="card-text">there are ${dto.numQuestion} questions in each quiz and it takes ${dto.time} minutes for the exam</p>
                        <button class="btn btn-info" data-toggle="modal" data-target="#myModal${counter.count}">
                            Take Quiz
                        </button>
                        <!-- Mini Modal -->
                        <div class="modal fade modal-mini modal-info" id="myModal${counter.count}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header justify-content-center">
                                        <h5>Ready for a quiz</h5>
                                    </div>
                                    <div class="modal-body justify-content-center">
                                        
                                        <p>Question : ${dto.numQuestion}</p>
                                        <p>Time: ${dto.time} minutes</p>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-link btn-neutral" data-dismiss="modal">Close</button>
                                        <a type="button" href="TakeQuizController?subjectID=${dto.subjectID}" class="btn btn-link btn-neutral">Take quiz</a>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--  End Modal -->
                    </div>
                </div>
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
