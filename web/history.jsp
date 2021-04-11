<%-- 
    Document   : history
    Created on : Feb 18, 2021, 10:41:28 PM
    Author     : tienx
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
        <title>History Page</title>            
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
                        <h2 class="title">History Quiz</h2>
                    </div>
                </div>
                
                
                <form class="form-inline" action="SearchHistoryController" method="POST" >
                                                           
                    <div class="form-group mb-2" style="margin: 5px">
                            Subject: 
                            <select style="margin: 5px" name="searchSubject" class="form-control">
                                <option>All</option>
                                <c:forEach items="${sessionScope.LISTSUBJECT}" var="subjectDTO">
                                    <option <c:if test="${sessionScope.SEARCHSUBJECT eq subjectDTO.subjectID}">selected="true"</c:if>>${subjectDTO.subjectID}</option>                               
                                </c:forEach>
                        </select>
                    </div>

                    <div class="form-group mb-2" style="margin: 5px">
                        <input type="hidden" name="index" value="1"/>
                        <button class="btn btn-info btn-round" type="submit" name="action" value="Search">Search</button>
                    </div>
                    
                </form>
                <!-- end search -->
                
                <table class="table table-striped">
                    <thead>
                        <tr class="bg-info">
                            <th scope="col">#</th>
                            <th scope="col">Subject</th>
                            <th scope="col">Number of correct</th>
                            <th scope="col">Point</th>
                            <th scope="col">Review</th>                                                    

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${sessionScope.LISTHISTORY}" var="dto" varStatus="counter">
                            <tr>

                                <th scope="row">${counter.count}</th>
                                <td>${dto.subjectID}</td>
                                <td>${dto.numOfCorrect}</td>
                                <td>${dto.point}</td>                                    
                                <td><a class="btn btn-success" href="ViewHistoryController?historyID=${dto.historyID}&numOfCorrect=${dto.numOfCorrect}&point=${dto.point}">Review</a></td>                                   

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                
                <ul class="pagination pagination-primary">
                <c:forEach begin="1" end="${sessionScope.NUMPAGE}" var="countPage">
                    <li class="page-item <c:if test="${sessionScope.INDEXHISTORYPAGE eq pageScope.countPage}"> active </c:if> ">
                        <a class="page-link" href="SearchHistoryController?index=${pageScope.countPage}&action=ChangePage">${pageScope.countPage}</a>
                        
                    </li>
                </c:forEach>
                </ul>
                        
                <c:if test="${sessionScope.LISTHISTORY eq null || empty sessionScope.LISTHISTORY}">
                    <h2>Not found any thing</h2>     
                </c:if>
                            
                
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

