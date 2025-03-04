<%-- 
    Document   : sidebar
    Created on : Feb 27, 2025, 9:12:26 AM
    Author     : A
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="bg-blue-900 text-white w-64 min-h-screen flex flex-col">
    <div class="flex items-center justify-center mt-10">
        <img alt="Logo" class="w-12 h-12 rounded-full" src="${pageContext.request.contextPath}/img/logo.jpg"/>
        <div class="ml-2 text-2xl font-bold nav_logo">
            <a href="#">Club<span>Manager</span></a>
        </div>
    </div>
    <div class="flex items-center mt-10 px-5">
        <img alt="User Avatar" class="w-12 h-12 rounded-full" src="${pageContext.request.contextPath}/img/logo_admin.jpg"/>
        <div class="ml-3">
            <p class="font-bold">${user.getFullName()}</p>
            <p class="text-sm">System Administrator</p>
        </div>
    </div>
    <nav class="mt-10">
        <ul>
            <li class="flex items-center px-5 py-2 bg-orange-500 text-white-900">
                <i class="fas fa-tachometer-alt"></i>
                <span class="ml-3">Dashboard</span>
            </li>
            <li class="flex items-center px-5 py-2 mt-2  cursor-pointer">
                <a href="${pageContext.request.contextPath}/User" class="flex items-center w-full">
                    <i class="fas fa-user-cog"></i>
                    <span class="ml-3">User Management</span>
                </a>
            </li>
            
            <li class="flex items-center px-5 py-2 mt-2">
                <i class="fas fa-users"></i>
                <a href="clubs?action=list" class="ml-3">Club Management</a>
            </li>

            <li class="flex items-center px-5 py-2 mt-2">
                <i class="fas fa-chart-line"></i>
                <span class="ml-3">Reports & Statistics</span>
            </li>
            <li class="flex items-center px-5 py-2 mt-2">
                <i class="fas fa-cogs"></i>
                <span class="ml-3">System Settings</span>
            </li>
        </ul>
    </nav>
    <form action="Logout" method="get" class="mt-auto mb-10 px-5">
        <button class="w-full bg-red-600 text-white py-2 rounded-lg flex items-center justify-center">
            <i class="fas fa-sign-out-alt"></i>
            <input type="submit" value="Logout" class="ml-3 bg-transparent border-none cursor-pointer">
        </button>
    </form>
</div>

