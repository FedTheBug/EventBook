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

# Pages Route
Route::get('/', 'PagesController@index');

# Abouts Route
Route::get('/about', 'PagesController@about');

# Services Route
Route::get('/services', 'PagesController@services');

# Events Route
Route::resource('events','EventsController');

# User Authentication Route
Auth::routes();

# Home Route
Route::get('/home', 'HomeController@index');


# Socialite
Route::get('auth/{provider}', 'Auth\RegisterController@redirectToProvider1');
Route::get('auth/{provider}/callback', 'Auth\RegisterController@handleProviderCallback1');
