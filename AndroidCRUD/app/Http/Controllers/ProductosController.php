<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;
use App\Http\Controllers\Controller;
use App\Product;

class ProductosController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        return response()->json(Product::all(), 200);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return response()->json(['message' => 'Use POST /productos con nombre, precio y cantidad.'], 200);
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $this->validate($request, [
            'nombre' => 'required|string|max:255',
            'precio' => 'required|integer|min:0',
            'cantidad' => 'required|integer|min:0',
        ]);

        $producto = Product::create([
            'nombre' => trim($request->input('nombre')),
            'precio' => (int) $request->input('precio'),
            'cantidad' => (int) $request->input('cantidad'),
        ]);

        return response()->json($producto, 201);
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $producto = Product::find($id);
        if (!$producto) {
            return response()->json(['error' => 'Producto no encontrado'], 404);
        }
        return response()->json($producto, 200);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        return $this->show($id);
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $producto = Product::find($id);
        if (!$producto) {
            return response()->json(['error' => 'Producto no encontrado'], 404);
        }

        $this->validate($request, [
            'nombre' => 'sometimes|required|string|max:255',
            'precio' => 'sometimes|required|integer|min:0',
            'cantidad' => 'sometimes|required|integer|min:0',
        ]);

        if ($request->has('nombre')) {
            $producto->nombre = trim($request->input('nombre'));
        }
        if ($request->has('precio')) {
            $producto->precio = (int) $request->input('precio');
        }
        if ($request->has('cantidad')) {
            $producto->cantidad = (int) $request->input('cantidad');
        }
        $producto->save();

        return response()->json($producto, 200);
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $producto = Product::find($id);
        if (!$producto) {
            return response()->json(['error' => 'Producto no encontrado'], 404);
        }

        $producto->delete();
        return response()->json(['deleted' => true, 'id' => (int) $id], 200);
    }
}
