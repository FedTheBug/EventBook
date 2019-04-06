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

//to make a route to home
Route::get('/', 'PagesController@index');

//to make a route to about
Route::get('/about', 'PagesController@about');

//to make a route to services
Route::get('/services', 'PagesController@services');

//to make route to event class
Route::resource('events','EventsController');

// to make route to auth
Auth::routes();

//route to home after logged in
Route::get('/home', 'HomeController@index');

//route to Facebook Socialite

Route::get('login/facebook', 'Auth\LoginController@redirectToProvider');
Route::get('login/facebook/callback', 'Auth\LoginController@handleProviderCallback');
