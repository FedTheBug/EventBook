<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\User;
use App\Event;

class HomeController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        $this->middleware('auth');
    }

    /**
     * Show the application dashboard.
     *
     * @return \Illuminate\Contracts\Support\Renderable
     */
    public function index()
    {
        $organizer_id = auth()->user()->id;
        $events = Event::where('organizer_id', '=', $organizer_id)->get();

 //     throw new \Exception($events);

        return view('home')->with('events', $events);
        
    }
    
}
