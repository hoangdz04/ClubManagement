<%-- 
    Document   : main-content
    Created on : Feb 27, 2025, 9:12:48 AM
    Author     : A
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="flex-1 p-10">
                <div class="flex items-center justify-between">
                    <div class="flex items-center">
                        <button class="text-2xl">
                            <i class="fas fa-bars"></i>
                        </button>
                        <div class="ml-5">
                            <form action="User" method="GET">
                            <input type="hidden" name="action" value="clubUsers">
                            <input type="submit"  class="bg-blue-600 text-white px-4 py-2 rounded-lg" value="All Club Members">                         
                            </form>
                        </div>
                    </div>
                    <div class="flex items-center">
                        <input class="px-4 py-2 rounded-lg border border-gray-300" placeholder="Search members..." type="text"/>
                        <div class="ml-5 flex items-center space-x-4">
                            <i class="fas fa-bell text-blue-900 text-2xl"></i>
                            <i class="fas fa-cog text-yellow-500 text-2xl"></i>
                            <i class="fas fa-envelope text-green-500 text-2xl relative">
                                <span class="absolute top-0 right-0 bg-red-500 text-white rounded-full px-2 py-1 text-xs">
                                    5
                                </span>
                            </i>
                        </div>
                    </div>
                </div>

                <div class="mt-10">
                    <h1 class="text-2xl font-bold flex items-center">
                        <i class="fas fa-calendar-alt mr-2"></i>
                        Club Activities Management
                    </h1>
                    <div class="flex mt-5 space-x-5">
                        <button class="bg-blue-600 text-white px-5 py-3 rounded-lg shadow-lg">
                            Event Planning
                        </button>
                        <button class="bg-blue-600 text-white px-5 py-3 rounded-lg shadow-lg">
                            Attendance Tracking
                        </button>
                        <button class="bg-blue-600 text-white px-5 py-3 rounded-lg shadow-lg">
                            Activity Reports
                        </button>
                        <button class="bg-blue-600 text-white px-5 py-3 rounded-lg shadow-lg">
                            Member Engagement
                        </button>
                    </div>

                    <div class="mt-10 bg-blue-900 text-white p-10 rounded-lg flex items-center">
                        <div class="flex-1">
                            <h2 class="text-4xl font-bold">
                                Manage ALL
                                <span class="text-yellow-500">
                                    Club Events & Activities
                                </span>
                            </h2>
                            <p class="mt-5 text-lg">
                                An active club is a thriving community!
                            </p>
                        </div>
                        <img class="w-1/3" height="200" src="${pageContext.request.contextPath}/img/illus.png" width="500"/>
                    </div>
                </div>
            </div>
</div>

