<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
//use App\Event;

class PagesController extends Controller
{
     /**
     * 
     * Home page of EventBook
     */
    public function index(){
        $events = 'welcome!';
        return view('pages.index')->with('events', $events);
    
   
    //return view('pages.index');

   
    
    }
    public function about(){
        $events = 'About us!';
        return view('pages.about')->with('events', $events);
    }
    public function Services(){
        $data = array(
            'title' => 'Services',
            'services' => ['Web Design', 'Programming', 'SEO']
        );
        return view('pages.services')->with($data);
}
}