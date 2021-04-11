<%-- 
    Document   : createQuestion
    Created on : Feb 17, 2021, 4:43:58 PM
    Author     : tienx
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
        <title>Create Page</title>            
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <link href="css/now-ui-kit.css" rel="stylesheet" />
        
    </head>
    <body class="landing-page sidebar-collapse">
        
        <nav class="navbar navbar-expand-lg bg-info">
            <div class="container">
                <div class="navbar-translate">
                    <a class="navbar-brand" href="admin.jsp">Quiz Online</a>
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
                        <h2 class="title">Create Page</h2>
                    </div>
                </div>
                
                
                <form  action="CreateQuestionController" method="POST" >
                    
                    <div class="form-group mb-2" style="margin: 5px">
                        <label>ID: </label>
                        <input class="form-control" type="text" name="questionID" placeholder="Question ID" value="${requestScope.QUESTIONID}" 
                               maxlength="30" required="true"/>
                    </div>
                    <p style="color: red;">${requestScope.INVALID}</p>
                    
                    <div class="form-group mb-2" style="margin: 5px">
                        <label>Question content: </label>
                        <textarea class="form-control" type="text" name="questionContent" placeholder="Question content"
                                  maxlength="500" required="true">${requestScope.QUESTIONCONTENT}</textarea>
                    </div>
                    
                    <div class="form-group mb-2" style="margin: 5px">
                        <label>Answer 1: </label>
                        <textarea class="form-control" type="text" name="answer1" placeholder="answer 1"
                                  maxlength="100" required="true">${requestScope.ANSWER1}</textarea>
                    </div>
                    
                    <div class="form-group mb-2" style="margin: 5px">
                        <label>Answer 2: </label>
                        <textarea class="form-control" type="text" name="answer2" placeholder="answer 2"
                                  maxlength="100" required="true">${requestScope.ANSWER2}</textarea>
                    </div>
                    
                    <div class="form-group mb-2" style="margin: 5px">
                        <label>Answer 3: </label>
                        <textarea class="form-control" type="text" name="answer3" placeholder="answer 3"
                                  maxlength="100" required="true">${requestScope.ANSWER3}</textarea>
                    </div>
                    
                    <div class="form-group mb-2" style="margin: 5px">
                        <label>Answer 4: </label>
                        <textarea class="form-control" type="text" name="answer4" placeholder="answer 4"
                                  maxlength="100" required="true">${requestScope.ANSWER4}</textarea>
                    </div>
                    
                    <div class="form-group mb-2" style="margin: 5px">
                            <label>Correct answer : </label>
                            <select style="margin: 5px" name="answerCorrect" class="form-control">

                                <option <c:if test="${requestScope.ANSWERCORRECT eq '1'}">selected="true"</c:if>>1</option>                               
                                <option <c:if test="${requestScope.ANSWERCORRECT eq '2'}">selected="true"</c:if>>2</option>
                                <option <c:if test="${requestScope.ANSWERCORRECT eq '3'}">selected="true"</c:if>>3</option>
                                <option <c:if test="${requestScope.ANSWERCORRECT eq '4'}">selected="true"</c:if>>4</option>
                                
                                
                        </select>
                    </div>
                    
                    <div class="form-group mb-2" style="margin: 5px">
                            <label>Subject ID: </label>
                            <select style="margin: 5px" name="subjectID" class="form-control">
                                
                                <c:forEach items="${sessionScope.LISTSUBJECT}" var="subjectDTO">
                                    <option <c:if test="${requestScope.SUBJECTID eq subjectDTO.subjectID}">selected="true"</c:if>>${subjectDTO.subjectID}</option>                               
                                </c:forEach>
                        </select>
                    </div>
                    
                    <div class="form-group mb-2" style="margin: 5px">
                        <label>Status:  </label>
                        <select style="margin: 5px" name="status" class="form-control">
                                                          
                            <option <c:if test="${requestScope.STATUS eq 'true'}">selected="true"</c:if>>true</option>
                            <option <c:if test="${requestScope.STATUS eq 'false'}">selected="true"</c:if>>false</option>

                        </select>
                    </div>

                    

                    <div class="form-group mb-2" style="margin: 5px">                       
                        <button class="btn btn-info btn-round" type="submit" >Create</button>
                    </div>
                    
                </form>
                
                <!-- end search -->
                
                
                            
                
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
