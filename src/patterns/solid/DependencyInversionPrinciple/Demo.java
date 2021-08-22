
//1. High-level modules should not depend on low-level modules
// Both should depend on abstractions

// 2. Abstractions should not depend on details
// Details should depend on abstractions

// (If you can, use interface or abstract classes instead of concrete classes
// You can substitute one implementation with another not breaking anything)




package patterns.solid.DependencyInversionPrinciple;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.javatuples.Triplet;

import javax.management.relation.Relation;


public class Demo {

    public static void main(String[] args) {
        Person parent = new Person("John");
        Person child1 = new Person("Chris");
        Person child2 = new Person("Matt");

        Relationships relationships = new Relationships();
        relationships.addParentAndChild(parent,child1);
        relationships.addParentAndChild(parent,child2);

        new Research(relationships);
    }
}

enum Relationship {
    CHILD,
    SIBLING,
    PARENT
}

class Person {
    public String name;

    public Person(String name){
        this.name = name;
    }
}

class Relationships implements RelationshipBrowser // low-level module (does not have business logic)
{
    private List<Triplet<Person, Relationship,Person>> relations = new ArrayList<>();

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    public void addParentAndChild(Person parent, Person child){
        relations.add(new Triplet<>(parent, Relationship.PARENT,child));
        relations.add(new Triplet<>(child, Relationship.CHILD,parent));
    }

    // logic should happen in low level module
    @Override
    public List<Person> findAllChildrenOf(String name) {
       return relations.stream()
                .filter(x -> x.getValue0().name.equals("John")
                        && x.getValue1() == Relationship.PARENT)
                .map(Triplet::getValue2)
                .collect(Collectors.toList());
    }
}

interface RelationshipBrowser
{
    List<Person> findAllChildrenOf(String name);

}

class Research { // high-level module (allows operations on low level constructs, closer to the user logic)






    public Research(RelationshipBrowser browser){ // we depend on abstraction (interface)
        List<Person> children = browser.findAllChildrenOf("John");
        for (Person child :children)
        System.out.println("John has a child called "+child.name);
    }
}