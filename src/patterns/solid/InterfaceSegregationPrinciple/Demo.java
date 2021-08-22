// Recommendation on how to split interfaces into smaller interfaces

package patterns.solid.InterfaceSegregationPrinciple;

public class Demo {
}

class Document {

}

// Bad way
interface Machine {
    void print (Document d);
    void fax(Document d);
    void scan(Document d);
}

// Good way
interface Printer
{
    void print (Document d);
}

interface Scanner{
    void scan(Document d);
}

interface Fax{
    void fax(Document d);
}


class MultifunctionPrinter implements Machine {

    @Override
    public void print(Document d) {
        // Implementation
    }

    @Override
    public void fax(Document d) {
        // Implementation
    }

    @Override
    public void scan(Document d) {
        // Implementation
    }
}

// Bad
// we need to implement methods for scanning and printing, although we don't need them
class OldFashionedPrinter implements Machine{

    @Override
    public void print(Document d) {
        // not using
    }

    @Override
    public void fax(Document d) {
        // Only using this one
    }

    @Override
    public void scan(Document d) {
        // not using
    }
}


// good way
class Photocopier implements Printer,Scanner
{
    @Override
    public void print(Document d) {
        // Implementation
    }

    @Override
    public void scan(Document d) {
        // Implementation
    }
}

interface MultiFunctionDevice extends Printer, Scanner{}

class MultifunctionMachine implements MultiFunctionDevice {
    @Override
    public void print(Document d) {
        // Implementation
    }

    @Override
    public void scan(Document d) {
        // Implementation
    }
}