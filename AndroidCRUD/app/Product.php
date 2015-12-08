<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Product extends Model
{
    protected $table="productos";

    protected $fillable = ['nombre','precio','cantidad'];

    public $timestamps = false;
}
