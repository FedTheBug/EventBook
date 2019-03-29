<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

//to make a route to index/home
Route::get('/', 'PagesController@index');

//to make a route to about
Route::get('/about', 'PagesController@about');

//to make a route to services
Route::get('/services', 'PagesController@services');
