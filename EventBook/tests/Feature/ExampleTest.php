<?php

namespace Tests\Feature;

use Tests\TestCase;
use Illuminate\Foundation\Testing\RefreshDatabase;

class ExampleTest extends TestCase
{
    /**
     * A basic test example.
     *
     * @return void
     */
    public function testBasicTest()
    {
        $response = $this->get('/');

        $response->assertSee('Welcome to the homepage of EventBook');
    }

    /**
     * Test example for About
     * 
     * @return void
     * 
     */
    public function testAbout()
    {
        $response = $this->get('/about');

        $response->assertSee('About Page of EventBook');
    }
    /**
     * Test example for Services
     * 
     * @return void
     * 
     */
    public function testServices()
    {
        $response = $this->get('/services');

        $response->assertSee('Services Page of EventBook');
    }

}
