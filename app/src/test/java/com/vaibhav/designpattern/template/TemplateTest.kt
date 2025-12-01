package com.vaibhav.designpattern.template

import org.junit.Test
import org.junit.Assert.assertEquals

class TemplateTest {

    // --- Chain of Responsibility Test ---
    data class StringRequest(val content: String)

    class LengthHandler : BaseHandler<StringRequest>() {
        override fun process(request: StringRequest) {
            println("LengthHandler: String length is ${request.content.length}")
        }

        override fun canHandle(request: StringRequest): Boolean {
            return request.content.length > 5
        }
    }

    class ContentHandler : BaseHandler<StringRequest>() {
        override fun process(request: StringRequest) {
            println("ContentHandler: String content is '${request.content}'")
        }
    }

    @Test
    fun `test Chain Template`() {
        println("--- Chain Template Test ---")
        val h1 = LengthHandler()
        val h2 = ContentHandler()
        h1.setNext(h2)

        // Length > 5, handled by LengthHandler
        h1.handle(StringRequest("Hello World"))

        // Length <= 5, passed to ContentHandler
        h1.handle(StringRequest("Hi"))
    }

    // --- Decorator Test ---
    class SimplePrinter : Component<String, String> {
        override fun operation(input: String): String {
            return input
        }
    }

    class UpperCaseDecorator(component: Component<String, String>) : Decorator<String, String>(component) {
        override fun operation(input: String): String {
            return super.operation(input).uppercase()
        }
    }

    class BracketDecorator(component: Component<String, String>) : Decorator<String, String>(component) {
        override fun operation(input: String): String {
            return "[${super.operation(input)}]"
        }
    }

    @Test
    fun `test Decorator Template`() {
        println("\n--- Decorator Template Test ---")
        val printer = BracketDecorator(UpperCaseDecorator(SimplePrinter()))
        val result = printer.operation("hello")
        println("Result: $result")
        assertEquals("[HELLO]", result)
    }

    // --- Observer Test ---
    class NewsAgency : Subject<String>()

    class NewsChannel(val name: String) : Observer<String> {
        override fun update(data: String) {
            println("$name received news: $data")
        }
    }

    @Test
    fun `test Observer Template`() {
        println("\n--- Observer Template Test ---")
        val agency = NewsAgency()
        val channel1 = NewsChannel("CNN")
        val channel2 = NewsChannel("BBC")

        agency.addObserver(channel1)
        agency.addObserver(channel2)

        agency.notifyObservers("Breaking News!")
    }

    // --- Strategy Test ---
    class AddStrategy : Strategy<Pair<Int, Int>, Int> {
        override fun execute(input: Pair<Int, Int>): Int = input.first + input.second
    }

    class MultiplyStrategy : Strategy<Pair<Int, Int>, Int> {
        override fun execute(input: Pair<Int, Int>): Int = input.first * input.second
    }

    @Test
    fun `test Strategy Template`() {
        println("\n--- Strategy Template Test ---")
        val context = Context(AddStrategy())
        assertEquals(10, context.executeStrategy(5 to 5))

        context.strategy = MultiplyStrategy()
        assertEquals(25, context.executeStrategy(5 to 5))
    }

    // --- Command Test ---
    class Light {
        fun turnOn() = println("Light is ON")
        fun turnOff() = println("Light is OFF")
    }

    class TurnOnCommand(private val light: Light) : Command<Unit> {
        override fun execute() = light.turnOn()
        override fun undo() = light.turnOff()
    }

    @Test
    fun `test Command Template`() {
        println("\n--- Command Template Test ---")
        val light = Light()
        val command = TurnOnCommand(light)
        val invoker = Invoker()

        invoker.executeCommand(command)
        invoker.undoLast()
    }

    // --- State Test ---
    class PlayerContext : StateContext<PlayerContext>(StoppedState())

    class StoppedState : State<PlayerContext> {
        override fun handle(context: PlayerContext) {
            println("Player is stopped. Starting...")
            context.state = PlayingState()
        }
    }

    class PlayingState : State<PlayerContext> {
        override fun handle(context: PlayerContext) {
            println("Player is playing. Stopping...")
            context.state = StoppedState()
        }
    }

    @Test
    fun `test State Template`() {
        println("\n--- State Template Test ---")
        val player = PlayerContext()
        player.request(player) // Stopped -> Playing
        player.request(player) // Playing -> Stopped
    }

    // --- Visitor Test ---
    interface Shape : Visitable<Shape>
    class Circle : Shape {
        override fun accept(visitor: Visitor<Shape>) = visitor.visit(this)
    }
    class Square : Shape {
        override fun accept(visitor: Visitor<Shape>) = visitor.visit(this)
    }

    class AreaVisitor : Visitor<Shape> {
        override fun visit(element: Shape) {
            when (element) {
                is Circle -> println("Calculating area of Circle")
                is Square -> println("Calculating area of Square")
            }
        }
    }

    @Test
    fun `test Visitor Template`() {
        println("\n--- Visitor Template Test ---")
        val shapes = listOf(Circle(), Square())
        val visitor = AreaVisitor()
        shapes.forEach { it.accept(visitor) }
    }

    // --- Template Method Test ---
    class DataProcessor : AbstractTemplate<String>() {
        override fun stepOne(context: String) = println("Step 1: Loading $context")
        override fun stepTwo(context: String) = println("Step 2: Processing $context")
        override fun stepThree(context: String) = println("Step 3: Saving $context")
    }

    @Test
    fun `test Template Method`() {
        println("\n--- Template Method Test ---")
        val processor = DataProcessor()
        processor.execute("Data.txt")
    }

    // --- Mediator Test ---
    class ChatMediator : Mediator<String> {
        override fun notify(sender: Colleague<String>, event: String, data: String?) {
            println("Mediator: $event received with data: $data")
        }
    }

    class User(mediator: Mediator<String>) : Colleague<String>(mediator) {
        override fun receive(event: String, data: String?) {}
    }

    @Test
    fun `test Mediator Template`() {
        println("\n--- Mediator Template Test ---")
        val mediator = ChatMediator()
        val user = User(mediator)
        user.send("Message", "Hello!")
    }

    // --- Memento Test ---
    @Test
    fun `test Memento Template`() {
        println("\n--- Memento Template Test ---")
        val originator = Originator("State 1")
        val caretaker = Caretaker<String>()

        caretaker.save(originator.save())
        originator.state = "State 2"
        println("Current State: ${originator.state}")

        val saved = caretaker.undo()
        if (saved != null) {
            originator.restore(saved)
            println("Restored State: ${originator.state}")
        }
        assertEquals("State 1", originator.state)
    }
}
