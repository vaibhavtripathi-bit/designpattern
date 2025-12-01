package com.vaibhav.designpattern.decorator.shape

class RedShapeDecorator(decoratedShape: Shape) : ShapeDecorator(decoratedShape) {
    override fun draw(log: MutableList<String>) {
        super.draw(log)
        setRedBorder(log)
    }

    private fun setRedBorder(log: MutableList<String>) {
        log.add("Red Border")
    }
}

class TransparentShapeDecorator(decoratedShape: Shape) : ShapeDecorator(decoratedShape) {
    override fun draw(log: MutableList<String>) {
        setTransparency(log)
        super.draw(log)
    }

    private fun setTransparency(log: MutableList<String>) {
        log.add("Transparency set")
    }
}
