<?php

/*
|--------------------------------------------------------------------------
| Modle Class
|--------------------------------------------------------------------------
|
| Model Class for Event
|
*/

namespace App;

use Illuminate\Database\Eloquent\Model;

class Event extends Model
{
    public function user(){
        return $this->belongsTo('App\User');
    }
}
