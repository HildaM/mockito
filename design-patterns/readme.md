[设计模式参考视频](https://www.bilibili.com/video/BV1jt41137zK)  
[设计模式最强专栏](https://blog.csdn.net/LoveLion/article/details/17517213)  
[练习Demo：design-patterns](https://github.com/cuiweiman/wang-wen-jun.git)

> [toc]


## 1. 引入
设计模式用于在特定的条件下为一些重复出现的软件设计问题提供合理的、有效的解决方案。
### 设计模式分类
- **创建型模式：** 描述怎样创建对象，主要特点是将对象的创建与使用分离。包含：简单工厂模式、工厂方法模式、抽象工厂模式、单例模式、原型模式、建造者模式。
- **结构型模式：** 用于描述如何将类或对象按某种布局组成更大的结构。包括：适配器模式、桥接模式、组合模式、装饰模式、外观模式、享元模式、代理模式。
- **行为型模式：** 包括：责任链模式、命令模式、解释器模式、迭代器模式、中介者模式、备忘录模式、观察者模式、状态模式、策略模式、模板方法模式、访问者模式。

### 六大原则
- **[总原则——开闭原则：](https://blog.csdn.net/LoveLion/article/details/7537584)** 软件实体应当对扩展开放，对修改关闭。尽量在不修改原代码的情况下进行扩展。（抽象化是开闭原则的关键）
- **[单一职责原则：](https://blog.csdn.net/lovelion/article/details/7536542)** 一个类只负责一个功能领域中的相应职责。是实现高内聚、低耦合的指导方针。
- **[里氏替换原则：](https://blog.csdn.net/lovelion/article/details/7540445)** 所有引用基类的地方，必须能透明地使用其子类对象。（代码中子类可以替换父类，程序不会出错）（子类可以扩展父类的功能，但不改变父类原有的功能。）
- **[依赖倒转原则：](https://blog.csdn.net/LoveLion/article/details/7562783)** 面向接口编程，而不是具体。(尽量引用层次搞得抽象层类，不要用具体类)
- **[接口隔离原则：](https://blog.csdn.net/LoveLion/article/details/7562842)** 使用多个专门的接口，而不是一个总接口。
- **[迪米特法则（最少知道原则）：](https://blog.csdn.net/LoveLion/article/details/7563445)** 一个类应当尽可能少地与其他实体发生相互作用。减少对象之间的交互或通信，降低系统类与类之间的耦合度。
- **[合成复用原则：](https://blog.csdn.net/LoveLion/article/details/7563441)** 尽量使用对象组合，而不是继承来达到复用的目的。

## 2. 创建型模式
### 2.1 简单工厂模式（Simple Factory Pattern）
不属于GoF 23中设计i模式，但是使用频繁。  
- **定义：** 创建一个工厂类，它可以根据参数的不同返回不同类的实例，被创建的实例通常都具有共同的父类。因为在简单工厂模式中用于创建实例的方法是静态(static)方法，因此简单工厂模式又被称为静态工厂方法(Static Factory Method)模式，它属于类创建型模式。
- **优点：** 
	1. 工厂类包含必要的判断逻辑；
	2. 客户端无需知道创建的产品类的类名，只需要知道类所对应的参数即可。
	3. 通过引入配置文件，可以在不修改客户端代码的情况下更换或增加新的具体产品类。
-  **缺点：** 
	1. 工厂类职责过重，一旦发生异常，整个系统都会受到影响。
	2. 简单工厂模式增加新的类，增加了系统的复杂度和理解难度。
	3. 系统扩展困难，一旦添加新类就需要修改工厂逻辑。
	4. 简单工厂模式由于使用了静态工厂方法，工厂角色无法形成基于继承的等级结构。
- **适用场景：**
	1. 工厂类负责创建的对象较少；
	2. 客户端只知道传入工厂类的参数，对如何创建对象并不关心。

```
graph BT
ConCreateProductA--> Product
ConCreateProductB --> Product
Factory --> ConCreateProductA
Factory --> ConCreateProductB
```
### 2.2 工厂方法模式（Factory Method Pattern）
简单工厂模式的弊端：工厂类职责较重，引入新类时需要修改工厂类代码，违背了“开闭原则”。为解决这个问题，工厂方法模式应运而生。
- **定义：** 定义一个用于创建对象的接口，让子类决定将哪一个类实例化。即提供一个抽象工厂接口并声明抽象工厂方法，其子类具体实现工厂方法，创建产品对象。
- **优点：** 
	1. 工厂方法模式隐藏了类实例化的细节，无需关心类的创建细节与类名。
	2. 抽象工厂类与工厂实现类 可以使工厂自主确定创建类对象。
	3. 扩展添加新类时无需修改原代码，只需添加一个工厂实现类即可。符合“开闭原则”。

- **缺点：** 
	1. 扩展新类时，需要添加新的抽象工厂实现类。
	2. 实现时可能要用到反射等技术，增加了系统的实现难度。
	
- **适用场景：** 不需要知道类名，只需要知道对应的工厂即可。具体的类由具体的工厂创建；抽象工厂类通过子类来指定创建的对象，利用了面向对象的多态性和里氏替换原则。共容易扩展。

### 2.3 抽象工厂模式（Abstract  Factory Pattern）
抽象工厂模式为创建一组对象提供了解决方案。与工厂方法模式相比，抽象工厂模式的具体工厂不只是创建一种产品，而是负责创建一个系列的产品。
- **定义：** 提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类。
- **优点：** 
	1. 抽象工厂模式隔离了具体类的生成，使得客户并不需要知道什么被创建。由于这种隔离，更换一个具体工厂就变得相对容易，所有的具体工厂都实现了抽象工厂中定义的那些公共接口，因此只需改变具体工厂的实例，就可以在某种程度上改变整个软件系统的行为。
	2. 当一个产品族中的多个对象被设计成一起工作时，它能够保证客户端始终只使用同一个产品族中的对象。
	3. 增加新的产品族很方便，无须修改已有系统，符合“开闭原则”。

- **缺点：** 增加新的产品等级结构麻烦，需要对原有系统进行较大的修改，甚至需要修改抽象层代码，这显然会带来较大的不便，违背了“开闭原则”。

### 2.4 单例模式（Singleton Pattern）确保对象的唯一性
- **定义：** 确保某一个类只有一个实例，而且自行实例化并向整个系统提供这个实例，这个类称为单例类，它提供全局访问的方法。三个要点：
	1. 该类只有一个实例；
	2. 自行创建实例；
	3. 向整个系统提供实例。

- **优点：**
	1. 提供了对唯一实例的受控访问。
	2. 系统内存只存在一个对象，节约了系统资源。
	3. 允许可变数目的实例。通过单例控制相似的方法获得指定个数的对象实例，既节省系统资源，又避免了单例对象共享过多 有损性能的问题。

- **缺点：**
	1. 没有抽象层，不便于扩展；
	2. 单例类职责过重，违背 “单一职责原则”；
	3. 实例化的共享对象长时间没被使用，会被垃圾回收器认定成垃圾而销毁并回收。下次利用时有需要重新实例化，将导致共享的单例对象状态的丢失。

#### 饿汉式
```java
public class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();
    /**
     * 需要将 构造方法私有化，不允许外部调用构造方法创建本类
     */
    private EagerSingleton() {
    }
    public static EagerSingleton getInstance() {
        return instance;
    }
}
```

#### 懒汉式 / 延迟加载
实例：为了避免多线程调用getInstance()方法，在方法上使用了 synchronized 关键字。但是此时会降低系统性能。
```java
public class LazySingleton {
    private static LazySingleton instance = null;
    private LazySingleton() { }
    public synchronized static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
```
改进1：synchronized关键字用在方法中。但仍存在问题：多线程同时判断 instance=null，那么线程A获取锁，线程B等待，等线程A创建实例后，线程B获取锁再次创建了实例，就不符合单例了。
```java
public class LazySingleton {
    private static LazySingleton instance = null;
    private LazySingleton() { }
    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                instance = new LazySingleton();
            }
        }
        return instance;
    }
}
```
改进2：***双重检查锁定（Double-Check Locking）***
```java
public class LazySingleton {
    private static LazySingleton instance = null;
    private LazySingleton() { }
    public static LazySingleton getInstance() {
        // 第一重 判断
        if (instance == null) {
            // 锁定代码块
            synchronized (LazySingleton.class) {
                // 第二重 判断
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
```

#### 饿汉式与懒汉式单例类的比较
- ***饿汉式*** 在类加载时自行实例化，无需考虑多线程问题，可以确保实例唯一性。调用速度和响应时间会优于懒汉式。但是从资源利用效率分析，系统加载时就需要创建饿汉式单例对象，加载时间会比较长。
- ***饿汉式*** 在首次使用时才实例化，无需一直占用系统资源，实现了延迟加载，但是必须处理好多线程同时访问的问题。而双重检测机制会使系统性能受到一定影响。

#### ***Initialization Demand Holder（IoDH）单例模式***
可以实现对象的延迟加载，并在保证线程安全的情况下使系统性能不受到影响。
> 静态单例对象  instance 没有作为 Singleton 的成员变量而直接实例化，因此类加载时不会实例化。在首次调用getInstance方法时，内部类HolderClass中定义了static类型的变量instance，此时会首先初始化这个成员变量，并且由Java虚拟机来保证其线程安全性，确保该成员变量只能初始化一次。由于没有任何线程锁定，因此不会对性能造成任何影响。
```java
public class Singleton {
    private Singleton() {}
    private static class HolderClass {
        private final static Singleton instance = new Singleton();
    }
    // 静态内部类 具备外部类的特性
    public static Singleton getInstance() {
        return HolderClass.instance;
    }
    public static void main(String[] args) {
        Singleton s1, s2;
        s1 = Singleton.getInstance();
        s2 = Singleton.getInstance();
        System.out.println(s1 == s2);
    }
}
```

#### ***枚举方式实现单例模式***
在对象延迟加载的前提下，保证线程安全。***枚举只会在类加载时装载一次，所以它是线程安全的（枚举类最终会被编译为final修饰的普通类，并且所有属性都会被 static 和 final 关键字修饰，所以枚举类在项目启动时就会被 JVM 加载并初始化，而这个执行过程是线程安全的，所以枚举类也是线程安全的类。）***  
```java
public class EnumSingleton {
    private enum Singleton {
        INSTANCE;
        private final EnumSingleton instance;
        Singleton() {
            instance = new EnumSingleton();
        }
        private EnumSingleton getInstance() {
            return instance;
        }
    }
    public static EnumSingleton getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
}
```



### 2.5 原型模式（Prototype Pattern）对象的克隆
***通过一个原型对象，克隆出多个一模一样的对象。***
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConCreatePrototype implements Cloneable {
    private String attr;
    @Override
    public ConCreatePrototype clone() {
        ConCreatePrototype prototype = null;
        try {
            prototype = (ConCreatePrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return prototype;
    }
    public static void main(String[] args) {
        ConCreatePrototype obj1 = new ConCreatePrototype("原型模式");
        ConCreatePrototype obj2 = obj1.clone();
        System.out.println(obj2.getAttr());

    }
}
```
#### 浅拷贝
如果原型对象的成员变量是值类型，将复制一份给克隆对象；如果原型对象的成员变量是引用类型，则将引用对象的地址复制一份给克隆对象，也就是说原型对象和克隆对象的成员变量指向相同的内存地址。
```java
@Data
class Address {
    private String add;
    private String[] arr;
}

public class ShallowCopy implements Cloneable {
    private Address address = new Address();

    public void setAdd(String add) {
        this.address.setAdd(add);
    }

    public String getAdd() {
        return this.address.getAdd();
    }

    public void setArr(String[] arr) {
        this.address.setArr(arr);
    }

    public String[] getArr() {
        return this.address.getArr();
    }

    @Override
    protected ShallowCopy clone() {
        ShallowCopy shallow = null;
        try {
            shallow = (ShallowCopy) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return shallow;
    }

    public static void main(String[] args) {
        ShallowCopy a = new ShallowCopy();
        a.setAdd("北京市朝阳区");
        a.setArr(new String[]{"Hello", "Java", "World"});
        System.out.println(a.getAdd());
        System.out.println(Arrays.asList(a.getArr()));

        ShallowCopy b = a.clone();
        System.out.println(b.getAdd());
        System.out.println(Arrays.asList(b.getArr()));

        System.out.println("======= 浅拷贝 拷贝的是 引用对象的地址 =======");
        b.setArr(new String[]{"Php", "Guava", "Python"});
        System.out.println(Arrays.asList(a.getArr()));
        System.out.println(Arrays.asList(b.getArr()));
    }
}
```
#### 深拷贝
无论原型对象的成员变量是值类型还是引用类型，都将复制一份给克隆对象，即深克隆将原型对象的引用对象也复制一份给克隆对象。
```java
@Data
@AllArgsConstructor
class Info implements Cloneable {
    private String add;
    private String[] arr;
    @Override
    protected Info clone() throws CloneNotSupportedException {
        return (Info) super.clone();
    }
}
@Data
public class DeepCopy implements Cloneable {
    public Info info;
    @Override
    protected DeepCopy clone() throws CloneNotSupportedException {
        DeepCopy deepCopy = (DeepCopy) super.clone();
        deepCopy.info = info.clone();
        return deepCopy;
    }
    public static void main(String[] args) throws CloneNotSupportedException {
        DeepCopy a = new DeepCopy();
        a.setInfo(new Info("上海市静安区", new String[]{"CSDN", "Byte", "BiliBili"}));
        DeepCopy b = a.clone();
        System.out.println(JSONObject.toJSONString(b));
        b.getInfo().setAdd("天津市和平区");
        b.getInfo().setArr(new String[]{"五大道", "小白楼"});

        System.out.println("a= " + JSONObject.toJSONString(a));
        System.out.println("b= " + JSONObject.toJSONString(b));
    }
}
```

### 2.6 建造者模式（Builder Pattern）复杂对象的组装与创建
- **定义：** 将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。
	- Builder（抽象建造者）：它为创建一个产品Product对象的各个部件指定抽象接口，在该接口中一般声明两类方法，一类方法是buildPartX()，它们用于创建复杂对象的各个部件；另一类方法是getResult()，它们用于返回复杂对象。Builder既可以是抽象类，也可以是接口。
	- ConcreteBuilder（具体建造者）：它实现了Builder接口，实现各个部件的具体构造和装配方法，定义并明确它所创建的复杂对象，也可以提供一个方法返回创建好的复杂产品对象。
	- Product（产品角色）：它是被构建的复杂对象，包含多个组成部件，具体建造者创建该产品的内部表示并定义它的装配过程。
	- Director（指挥者）：指挥者又称为导演类，它负责安排复杂对象的建造次序，指挥者与抽象建造者之间存在关联关系，可以在其construct()建造方法中调用建造者对象的部件构造与装配方法，完成复杂对象的建造。客户端一般只需要与指挥者进行交互，在客户端确定具体建造者的类型，并实例化具体建造者对象（也可以通过配置文件和反射机制），然后通过指挥者类的构造函数或者Setter方法将该对象传入指挥者类中。

- **优点：** 
	1. 在建造者模式中，客户端不必知道产品内部组成的细节，将产品本身与产品的创建过程解耦，使得相同的创建过程可以创建不同的产品对象。
	2. 每一个具体建造者都相对独立，而与其他的具体建造者无关，因此可以很方便地替换具体建造者或增加新的具体建造者，用户使用不同的具体建造者即可得到不同的产品对象。
	3. 可以更加精细地控制产品的创建过程。
- **缺点：** 
	1. 建造者模式所创建的产品一般具有较多的共同点，其组成部分相似，如果产品之间的差异性很大，例如很多组成部分都不相同，不适合使用建造者模式，因此其使用范围受到一定的限制。
	2. 如果产品的内部变化复杂，可能会导致需要定义很多具体建造者类来实现这种变化，导致系统变得很庞大，增加系统的理解难度和运行成本。


## 3. 结构型模式

### 3.1 适配器模式（Adapter Pattern）不兼容结构的协调
- **定义：** 将一个接口转换成客户希望的另一个接口，使接口不兼容的那些类可以一起工作，其别名为包装器(Wrapper)。（在适配器模式定义中所提及的接口是指广义的接口，它可以表示一个方法或者方法的集合。）
- **优点：** 将目标类和适配者类解耦；增加了类的透明性和复用性；灵活性和扩展性比较好。
- **缺点：** 适配者类不能被final修饰；java不支持多重继承，一次最多只能适配一个适配者类，不能同时适配多个适配者；适配器模式中的目标抽象类只能为接口，不能为类。

### 3.2 桥接模式（Bridge Pattern）处理多维度变化
- **定义：** 将抽象部分与它的实现部分分离，使它们都可以独立地变化。（在桥接模式中体现了很多面向对象设计原则的思想，包括“单一职责原则”、“开闭原则”、“合成复用原则”、“里氏代换原则”、“依赖倒转原则”。）
- **优点：** 
	1. 分离抽象接口及其实现部分。
	2. 在很多情况下，桥接模式可以取代多层继承方案，多层继承方案违背了“单一职责原则”，复用性较差，且类的个数非常多，桥接模式是比多层继承方案更好的解决方法，它极大减少了子类的个数。
	3. 桥接模式提高了系统的可扩展性，在两个变化维度中任意扩展一个维度，都不需要修改原有系统，符合“开闭原则”。  
- **缺点：** 
	1. 桥接模式的使用会增加系统的理解与设计难度，由于关联关系建立在抽象层，要求开发者一开始就针对抽象层进行设计与编程。
	2. 桥接模式要求正确识别出系统中两个独立变化的维度，因此其使用范围具有一定的局限性，如何正确识别两个独立维度也需要一定的经验积累。

### 3.3 组合模式（Composite Pattern）树形结构的处理
- **定义：** 组合多个对象形成树形结构以表示具有“整体—部分”关系的层次结构。组合模式对单个对象（即叶子对象）和组合对象（即容器对象）的使用具有一致性。
- **优点：** 
	1. 组合模式可以清楚地定义分层次的复杂对象，表示对象的全部或部分层次，它让客户端忽略了层次的差异，方便对整个层次结构进行控制。
	2. 客户端可以一致地使用一个组合结构或其中单个对象，不必关心处理的是单个对象还是整个组合结构，简化了客户端代码。
	3.  在组合模式中增加新的容器构件和叶子构件都很方便，无须对现有类库进行任何修改，符合“开闭原则”。
	4. 组合模式为树形结构的面向对象实现提供了一种灵活的解决方案，通过叶子对象和容器对象的递归组合，可以形成复杂的树形结构，但对树形结构的控制却非常简单。

- **缺点：** 在增加新构件时很难对容器中的构件类型进行限制。有时候我们希望一个容器中只能有某些特定类型的对象，例如在某个文件夹中只能包含文本文件，使用组合模式时，不能依赖类型系统来施加这些约束，因为它们都来自于相同的抽象层，在这种情况下，必须通过在运行时进行类型检查来实现，这个实现过程较为复杂。

### 3.4 装饰模式（Decorator Pattern）扩展系统功能
- **定义：** 动态地给一个对象增加一些额外的职责，就增加对象功能来说，装饰模式比生成子类实现更为灵活。
- **优点：** 
	1. 对于扩展一个对象的功能，装饰模式比继承更加灵活性，不会导致类的个数急剧增加。
	2. 可以通过一种动态的方式来扩展一个对象的功能，通过配置文件可以在运行时选择不同的具体装饰类，从而实现不同的行为。
	3. 可以对一个对象进行多次装饰，通过使用不同的具体装饰类以及这些装饰类的排列组合，可以创造出很多不同行为的组合，得到功能更为强大的对象。
	4. 具体构件类与具体装饰类可以独立变化，用户可以根据需要增加新的具体构件类和具体装饰类，原有类库代码无须改变，符合“开闭原则”。
- **缺点：** 
	1. 使用装饰模式进行系统设计时将产生很多小对象，这些对象的区别在于它们之间相互连接的方式有所不同，而不是它们的类或者属性值有所不同，大量小对象的产生势必会占用更多的系统资源，在一定程序上影响程序的性能。
	2. 装饰模式提供了一种比继承更加灵活机动的解决方案，但同时也意味着比继承更加易于出错，排错也很困难，对于多次装饰的对象，调试时寻找错误可能需要逐级排查，较为繁琐。

### 3.5 外观模式（Facade Pattern）深入浅出
- **定义：** 为子系统中的一组接口提供一个统一的入口。外观模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。（是迪米特法则的一种具体实现，通过引入一个新的外观角色可以降低原有系统的复杂度，同时降低客户类与子系统的耦合度。）
- **优点：** 
	1. 它对客户端屏蔽了子系统组件，减少了客户端所需处理的对象数目，并使得子系统使用起来更加容易。通过引入外观模式，客户端代码将变得很简单，与之关联的对象也很少。
	2. 它实现了子系统与客户端之间的松耦合关系，这使得子系统的变化不会影响到调用它的客户端，只需要调整外观类即可。
	3. 一个子系统的修改对其他子系统没有任何影响，而且子系统内部变化也不会影响到外观对象。
- **缺点：** 
	1. 不能很好地限制客户端直接使用子系统类，如果对客户端访问子系统类做太多的限制则减少了可变性和灵活性。
	2. 如果设计不当，增加新的子系统可能需要修改外观类的源代码，违背了开闭原则。

### 3.6 享元模式（Flyweight Pattern）实现对象的复用
- **定义：** 运用共享技术有效地支持大量细粒度对象的复用。系统只使用少量的对象，而这些对象都很相似，状态变化很小，可以实现对象的多次复用。（ 当一个软件系统在运行时产生的对象数量太多，将导致运行代价过高，带来系统性能下降等问题。享元模式通过共享技术实现相同或相似对象的重用，在逻辑上每一个出现的字符都有一个对象与之对应，然而在物理上它们却共享同一个享元对象，这个对象可以出现在一个字符串的不同地方，相同的字符对象都指向同一个实例，在享元模式中，存储这些共享实例对象的地方称为享元池(Flyweight Pool)）
	1. 享元模式结构较为复杂，一般结合工厂模式一起使用，享元工厂类的作用在于提供一个用于存储享元对象的享元池，当用户需要对象时，首先从享元池中获取，如果享元池中不存在，则创建一个新的享元对象返回给用户，并在享元池中保存该新增对象。
	2. 在实现享元工厂类时，经常使用单例模式和简单工厂模式，确保享元工厂对象的唯一性，并提供工厂方法来向客户端返回享元对象。
	3. JDK类库中的String类使用了享元模式。如果每次执行类似String str1="abcd"的操作时都创建一个新的字符串对象将导致内存开销很大，因此如果第一次创建了内容为"abcd"的字符串对象str1，下一次再创建内容相同的字符串对象str2时会将它的引用指向"abcd"，不会重新分配内存空间，从而实现了"abcd"在内存中的共享。
- **单纯享元模式：** 在单纯享元模式中，所有的具体享元类都是可以共享的，不存在非共享具体享元类。  
- **复合享元模式：** 将一些单纯享元对象使用组合模式加以组合，还可以形成复合享元对象，这样的复合享元对象本身不能共享，但是它们可以分解成单纯享元对象，而后者则可以共享。
- 通过复合享元模式，可以确保复合享元类CompositeConcreteFlyweight中所包含的每个单纯享元类ConcreteFlyweight都具有相同的外部状态，而这些单纯享元的内部状态往往可以不同。**如果希望为多个内部状态不同的享元对象设置相同的外部状态，可以考虑使用复合享元模式。**
- **优点：** 
	1. 可以极大减少内存中对象的数量，使得相同或相似对象在内存中只保存一份，从而可以节约系统资源，提高系统性能。
	2. 享元模式的外部状态相对独立，而且不会影响其内部状态，从而使得享元对象可以在不同的环境中被共享。
- **缺点：** 
	1. 享元模式使得系统变得复杂，需要分离出内部状态和外部状态，这使得程序的逻辑复杂化。
	2. 为了使对象可以共享，享元模式需要将享元对象的部分状态外部化，而读取外部状态将使得运行时间变长。

### 3.7 代理模式（Proxy Pattern）
>当无法直接访问某个对象或访问某个对象存在困难时可以通过一个代理对象来间接访问，为了保证客户端使用的透明性，所访问的真实对象与代理对象需要实现相同的接口。根据代理模式的使用目的不同，代理模式又可以分为多种类型，例如保护代理、远程代理、虚拟代理、缓冲代理等，它们应用于不同的场合，满足用户的不同需求。  

- **定义：** 给某一个对象提供一个代理或占位符，并由代理对象来控制对原对象的访问。
	1. 远程代理(Remote Proxy)：为一个位于不同的地址空间的对象提供一个本地的代理对象，这个不同的地址空间可以是在同一台主机中，也可是在另一台主机中。
	2. 虚拟代理(Virtual Proxy)：如果需要创建一个资源消耗较大的对象，先创建一个消耗相对较小的对象来表示，真实对象只在需要时才会被真正创建。
	3. 保护代理(Protect Proxy)：控制对一个对象的访问，可以给不同的用户提供不同级别的使用权限。
	4. 缓冲代理(Cache Proxy)：为某一个目标操作的结果提供临时的存储空间，以便多个客户端可以共享这些结果。
	5. 智能引用代理(Smart Reference Proxy)：当一个对象被引用时，提供一些额外的操作，例如将对象被调用的次数记录下来等。  
- **优点：**
	1. 能够协调调用者和被调用者，在一定程度上降低了系统的耦合度。
	2. 客户端可以针对抽象主题角色进行编程，增加和更换代理类无须修改源代码，符合开闭原则，系统具有较好的灵活性和可扩展性。  
- **缺点：**
	1. 由于在客户端和真实主题之间增加了代理对象，因此有些类型的代理模式可能会造成请求的处理速度变慢，例如保护代理。
	2. 实现代理模式需要额外的工作，而且有些代理模式的实现过程较为复杂，例如远程代理。
- **适用场景：**
	1. 当客户端对象需要访问远程主机中的对象时可以使用远程代理。
	2. 当需要用一个消耗资源较少的对象来代表一个消耗资源较多的对象，从而降低系统开销、缩短运行时间时可以使用虚拟代理，例如一个对象需要很长时间才能完成加载时。
	3. 当需要为某一个被频繁访问的操作结果提供一个临时存储空间，以供多个客户端共享访问这些结果时可以使用缓冲代理。通过使用缓冲代理，系统无须在客户端每一次访问时都重新执行操作，只需直接从临时缓冲区获取操作结果即可。
	4. 当需要控制对一个对象的访问，为不同用户提供不同级别的访问权限时可以使用保护代理。
	5. 当需要为一个对象的访问（引用）提供一些额外的操作时可以使用智能引用代理。

## 4. 行为型模式
### 4.1 职责链模式（Chain of Responsibility Pattern）请求的链式处理
- **定义：** 避免请求发送者与接收者耦合在一起，让多个对象都有可能接收请求，将这些对象连接成一条链，并且沿着这条链传递请求，直到有对象处理它为止。职责链模式并不创建职责链，职责链的创建工作必须由系统的其他部分来完成，一般是在使用该职责链的客户端中创建职责链。
	1. **纯的职责链模式：**  一个纯的职责链模式要求一个具体处理者对象只能在两个行为中选择一个：要么承担全部责任，要么将责任推给下家，不允许出现某一个具体处理者对象在承担了一部分或全部责任后又将责任向下传递的情况。而且在纯的职责链模式中，要求一个请求必须被某一个处理者对象所接收，不能出现某个请求未被任何一个处理者对象处理的情况。
	2. **不纯的职责链模式：** 在一个不纯的职责链模式中允许某个请求被一个具体处理者部分处理后再向下传递，或者一个具体处理者处理完某请求后其后继处理者可以继续处理该请求，而且一个请求可以最终不被任何处理者对象所接收。每一级组件在接收到事件时，都可以处理此事件，而不论此事件是否在上一级已得到处理，还存在事件未被处理的情况。

- **优点：** 
	1. 职责链模式使得一个对象无须知道是其他哪一个对象处理其请求，对象仅需知道该请求会被处理即可，接收者和发送者都没有对方的明确信息，且链中的对象不需要知道链的结构，由客户端负责链的创建，降低了系统的耦合度。
	2. 请求处理对象仅需维持一个指向其后继者的引用，而不需要维持它对所有的候选处理者的引用，可简化对象的相互连接。
	3. 在给对象分派职责时，职责链可以给我们更多的灵活性，可以通过在运行时对该链进行动态的增加或修改来增加或改变处理一个请求的职责。
	4. 在系统中增加一个新的具体请求处理者时无须修改原有系统的代码，只需要在客户端重新建链即可，从这一点来看是符合“开闭原则”的。
- **缺点：** 
	1. 由于一个请求没有明确的接收者，那么就不能保证它一定会被处理，该请求可能一直到链的末端都得不到处理；一个请求也可能因职责链没有被正确配置而得不到处理。
	2.  对于比较长的职责链，请求的处理可能涉及到多个处理对象，系统性能将受到一定影响，而且在进行代码调试时不太方便。
	3.  如果建链不当，可能会造成循环调用，将导致系统陷入死循环。
- **适用场景：** 
	1. 有多个对象可以处理同一个请求，具体哪个对象处理该请求待运行时刻再确定，客户端只需将请求提交到链上，而无须关心请求的处理对象是谁以及它是如何处理的。
	2. 在不明确指定接收者的情况下，向多个对象中的一个提交一个请求。
	3. 可动态指定一组对象处理请求，客户端可以动态创建职责链来处理请求，还可以改变链中处理者之间的先后次序。 

### 4.2 命令模式（Command Pattern）请求发送者与接收者解耦
- **定义：** 将一个请求封装为一个对象，从而让我们可用不同的请求对客户进行参数化；对请求排队或者记录请求日志，以及支持可撤销的操作。命令模式可以将请求发送者和接收者完全解耦，发送者与接收者之间没有直接引用关系，发送请求的对象只需要知道如何发送请求，而不必知道如何完成请求。
	1. 命令模式的本质是对请求进行封装，一个请求对应于一个命令，将发出命令的责任和执行命令的责任分割开。每一个命令都是一个操作：请求的一方发出请求要求执行一个操作；接收的一方收到请求，并执行相应的操作。命令模式允许请求的一方和接收的一方独立开来，使得请求的一方不必知道接收请求的一方的接口，更不必知道请求如何被接收、操作是否被执行、何时被执行，以及是怎么被执行的。
	2. 命令模式的关键在于引入了抽象命令类，请求发送者针对抽象命令类编程，只有实现了抽象命令类的具体命令才与请求接收者相关联。
	3. **宏命令：** 又称为组合命令，它是组合模式和命令模式联用的产物。宏命令是一个具体命令类，它拥有一个集合属性，在该集合中包含了对其他命令对象的引用。通常宏命令不直接与请求接收者交互，而是通过它的成员来调用接收者的方法。执行一个宏命令将触发多个具体命令的执行，从而实现对命令的批处理。

- **优点：** 
	1. 降低系统的耦合度。
	2. 新的命令可以很容易地加入到系统中。
	3.  可以比较容易地设计一个命令队列或宏命令（组合命令）。
	4.  为请求的撤销(Undo)和恢复(Redo)操作提供了一种设计和实现方案。
- **缺点：** 使用命令模式可能会导致某些系统有过多的具体命令类。

### 4.3 解释器模式（Interpreter Pattern）自定义语言的实现
- **定义：** 定义一个语言的文法，并且建立一个解释器来解释该语言中的句子，这里的“语言”是指使用规定格式和语法的代码。解释器模式描述了如何为简单的语言定义一个文法，如何在该语言中表示一个句子，以及如何解释这些句子。
- **优点：** 
	1. 易于改变和扩展文法。
	2. 每一条文法规则都可以表示为一个类，因此可以方便地实现一个简单的语言。
	3.  实现文法较为容易。
	4.  增加新的解释表达式较为方便。
- **缺点：** 
	1. 对于复杂文法难以维护。
	2. 执行效率较低。
（ 解释器模式可以说是所有设计模式中难度较大、使用频率较低的一个模式）

### 4.4 迭代器模式（Iterator Pattern）遍历聚合对象中的元素
- **定义：** 提供一种方法来访问聚合对象，而不用暴露这个对象的内部表示。
- **优点：** 
	1. 支持以不同的方式遍历一个聚合对象，在同一个聚合对象上可以定义多种遍历方式。
	2. 迭代器简化了聚合类。
	3. 在迭代器模式中，由于引入了抽象层，增加新的聚合类和迭代器类都很方便，无须修改原有代码，满足“开闭原则”的要求。
- **缺点：** 
	1.  由于迭代器模式将存储数据和遍历数据的职责分离，增加新的聚合类需要对应增加新的迭代器类，类的个数成对增加，这在一定程度上增加了系统的复杂性。
	2.  抽象迭代器的设计难度较大，需要充分考虑到系统将来的扩展，例如JDK内置迭代器Iterator就无法实现逆向遍历，如果需要实现逆向遍历，只能通过其子类ListIterator等来实现，而ListIterator迭代器无法用于操作Set类型的聚合对象。在自定义迭代器时，创建一个考虑全面的抽象迭代器并不是件很容易的事情。
- ** 适用场景：** 
	1. 访问一个聚合对象的内容而无须暴露它的内部表示。
	2. 需要为一个聚合对象提供多种遍历方式。
	3. 为遍历不同的聚合结构提供一个统一的接口，在该接口的实现类中为不同的聚合结构提供不同的遍历方式，而客户端可以一致性地操作该接口。

### 4.5 中介者模式（Mediator Pattern）协调多个对象之间的交互
- **定义：** 用一个中介对象（中介者）来封装一系列的对象交互，中介者使各对象不需要显式地相互引用，从而使其耦合松散，而且可以独立地改变它们之间的交互。中介者模式又称为调停者模式。
	1. 如果在一个系统中对象之间的联系呈现为网状结构，对象之间存在大量的多对多联系，将导致系统非常复杂，这些对象既会影响别的对象，也会被别的对象所影响，这些对象称为***同事对象***，它们之间通过彼此的相互作用实现系统的行为。在网状结构中，几乎每个对象都需要与其他对象发生相互作用，而这种相互作用表现为一个对象与另外一个对象的直接耦合，这将导致一个过度耦合的系统。***中介者模式可以使对象之间的关系数量急剧减少***
	2. **中介者模式是“迪米特法则”的一个典型应用。**
- **优点：**
	1. 简化了对象之间的交互。
	2. 可将各同事对象解耦。
	3. 可以减少子类生成。
- **缺点：** 在具体中介者类中包含了大量同事之间的交互细节，可能会导致具体中介者类非常复杂，使得系统难以维护。
- **适用场景：** 
	1. 系统中对象之间存在复杂的引用关系，系统结构混乱且难以理解。
	2.  一个对象由于引用了其他很多对象并且直接和这些对象通信，导致难以复用该对象。
	3.  想通过一个中间类来封装多个类中的行为，而又不想生成太多的子类。

### 4.6 备忘录模式（Memento Pattern）撤销功能的实现
- **定义：** 在不破坏封装的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，这样可以在以后将对象恢复到原先保存的状态。
	1. 备忘录模式提供了一种状态恢复的实现机制，使得用户可以方便地回到一个特定的历史步骤，当新的状态无效或者存在问题时，可以使用暂时存储起来的备忘录将状态复原，当前很多软件都提供了撤销(Undo)操作，其中就使用了备忘录模式。
- **优点：** 
	1. 它提供了一种状态恢复的实现机制，使得用户可以方便地回到一个特定的历史步骤，当新的状态无效或者存在问题时，可以使用暂时存储起来的备忘录将状态复原。
	2. 备忘录实现了对信息的封装，一个备忘录对象是一种原发器对象状态的表示，不会被其他代码所改动。备忘录保存了原发器的状态，采用列表、堆栈等集合来存储备忘录对象可以实现多次撤销操作。
- **缺点：** 资源消耗过大，如果需要保存的原发器类的成员变量太多，就不可避免需要占用大量的存储空间，每保存一次对象的状态都需要消耗一定的系统资源。
- **适用场景：** 
	1. 保存一个对象在某一个时刻的全部状态或部分状态，这样以后需要时它能够恢复到先前的状态，实现撤销操作。
	2. 防止外界对象破坏一个对象历史状态的封装性，避免将对象历史状态的实现细节暴露给外界对象。


### 4.7 观察者模式（Observer Pattern）对象间的联动
- **定义：** 定义对象之间的一种一对多依赖关系，使得每当一个对象状态发生改变时，其相关依赖对象皆得到通知并被自动更新。观察者模式的别名包括发布-订阅（Publish/Subscribe）模式、模型-视图（Model/View）模式、源-监听器（Source/Listener）模式或从属者（Dependents）模式。
	1. 观察者模式是使用频率最高的设计模式之一，它用于建立一种对象与对象之间的依赖关系，一个对象发生改变时将自动通知其他对象，其他对象将相应作出反应。在观察者模式中，发生改变的对象称为观察目标，而被通知的对象称为观察者，一个观察目标可以对应多个观察者，而且这些观察者之间可以没有任何相互联系，可以根据需要增加和删除观察者，使得系统更易于扩展。
	2. 观察者模式结构中通常包括观察目标和观察者两个继承层次结构。
	3. 观察者模式在Java语言中的地位非常重要。在JDK的java.util包中，提供了Observable类以及Observer接口，它们构成了JDK对观察者模式的支持。可以直接使用Observer接口和Observable类来作为观察者模式的抽象层，再自定义具体观察者类和具体观察目标类，通过使用JDK中的Observer接口和Observable类，可以更加方便地在Java语言中应用观察者模式。

- **优点：** 
	1. 观察者模式可以实现表示层和数据逻辑层的分离，定义了稳定的消息更新传递机制，并抽象了更新接口，使得可以有各种各样不同的表示层充当具体观察者角色。
	2. 观察者模式在观察目标和观察者之间建立一个抽象的耦合。观察目标只需要维持一个抽象观察者的集合，无须了解其具体观察者。由于观察目标和观察者没有紧密地耦合在一起，因此它们可以属于不同的抽象化层次。
	3. 观察者模式支持广播通信，观察目标会向所有已注册的观察者对象发送通知，简化了一对多系统设计的难度。
	4. 观察者模式满足“开闭原则”的要求，增加新的具体观察者无须修改原有系统代码，在具体观察者与观察目标之间不存在关联关系的情况下，增加新的观察目标也很方便。
- **缺点：**
	1. 如果一个观察目标对象有很多直接和间接观察者，通知所有的观察者会花费很多时间。
	2. 如果在观察者和观察目标之间存在循环依赖，观察目标会触发它们之间进行循环调用，可能导致系统崩溃。
	3. 观察者模式没有相应的机制让观察者知道所观察的目标对象是怎么发生变化的，而仅仅只是知道观察目标发生了变化。

- **适用场景：** 
	1. 一个抽象模型有两个方面，其中一个方面依赖于另一个方面，将这两个方面封装在独立的对象中使它们可以各自独立地改变和复用。
	2. 一个对象的改变将导致一个或多个其他对象也发生改变，而并不知道具体有多少对象将发生改变，也不知道这些对象是谁。
	3. 需要在系统中创建一个触发链，A对象的行为将影响B对象，B对象的行为将影响C对象……，可以使用观察者模式创建一种链式触发机制。

### 4.8 状态模式（State Pattern）处理对象的多种状态及其相互转换
- **定义：** 允许一个对象在其内部状态改变时改变它的行为，对象看起来似乎修改了它的类。

- **优点：** 
	1. 封装了状态的转换规则，在状态模式中可以将状态的转换代码封装在环境类或者具体状态类中，可以对状态转换代码进行集中管理，而不是分散在一个个业务方法中。
	2. 将所有与某个状态有关的行为放到一个类中，只需要注入一个不同的状态对象即可使环境对象拥有不同的行为。
	3. 允许状态转换逻辑与状态对象合成一体，而不是提供一个巨大的条件语句块，状态模式可以让我们避免使用庞大的条件语句来将业务方法和状态转换代码交织在一起。
	4. 可以让多个环境对象共享一个状态对象，从而减少系统中对象的个数。

- **缺点：**
	1. 状态模式的使用必然会增加系统中类和对象的个数，导致系统运行开销增大。
	2. 状态模式的结构与实现都较为复杂，如果使用不当将导致程序结构和代码的混乱，增加系统设计的难度。
	3. 状态模式对“开闭原则”的支持并不太好，增加新的状态类需要修改那些负责状态转换的源代码，否则无法转换到新增状态；而且修改某个状态类的行为也需修改对应类的源代码。

- **适用场景：** 
	1. 对象的行为依赖于它的状态（如某些属性值），状态的改变将导致行为的变化。
	2. 在代码中包含大量与对象状态有关的条件语句，这些条件语句的出现，会导致代码的可维护性和灵活性变差，不能方便地增加和删除状态，并且导致客户类与类库之间的耦合增强。

### 4.9 策略模式（Strategy Pattern）算法的封装与切换
- **定义：** 定义一系列算法类，将每一个算法封装起来，并让它们可以相互替换，策略模式让算法独立于使用它的客户而变化。
	1. 实现某一个功能有多条途径，每一条途径对应一种算法，此时我们可以使用一种设计模式来实现灵活地选择解决途径，也能够方便地增加新的解决途径。策略模式是为了适应算法灵活性而产生的设计模式。
	2. 策略模式的主要目的是将算法的定义与使用分开，也就是将算法的行为和环境分开。

- **优点：** 
	1. 策略模式提供了对“开闭原则”的完美支持，用户可以在不修改原有系统的基础上选择算法或行为，也可以灵活地增加新的算法或行为。
	2. 策略模式提供了管理相关的算法族的办法。策略类的等级结构定义了一个算法或行为族，恰当使用继承可以把公共的代码移到抽象策略类中，从而避免重复的代码。
	3. 策略模式提供了一种可以替换继承关系的办法。如果不使用策略模式，那么使用算法的环境类就可能会有一些子类，每一个子类提供一种不同的算法。但是，这样一来算法的使用就和算法本身混在一起，不符合“单一职责原则”，决定使用哪一种算法的逻辑和该算法本身混合在一起，从而不可能再独立演化；而且使用继承无法实现算法或行为在程序运行时的动态切换。
	4. 使用策略模式可以避免多重条件选择语句。多重条件选择语句不易维护，它把采取哪一种算法或行为的逻辑与算法或行为本身的实现逻辑混合在一起，将它们全部硬编码(Hard Coding)在一个庞大的多重条件选择语句中，比直接继承环境类的办法还要原始和落后。
	5. 策略模式提供了一种算法的复用机制，由于将算法单独提取出来封装在策略类中，因此不同的环境类可以方便地复用这些策略类。

- **缺点：**
	1. 客户端必须知道所有的策略类，并自行决定使用哪一个策略类。这就意味着客户端必须理解这些算法的区别，以便适时选择恰当的算法。换言之，策略模式只适用于客户端知道所有的算法或行为的情况。
	2. 策略模式将造成系统产生很多具体策略类，任何细小的变化都将导致系统要增加一个新的具体策略类。
	3. 无法同时在客户端使用多个策略类，也就是说，在使用策略模式时，客户端每次只能使用一个策略类，不支持使用一个策略类完成部分功能后再使用另一个策略类来完成剩余功能的情况。

- **适用场景：** 
	1. 一个系统需要动态地在几种算法中选择一种，那么可以将这些算法封装到一个个的具体算法类中，而这些具体算法类都是一个抽象算法类的子类。换言之，这些具体算法类均有统一的接口，根据“里氏代换原则”和面向对象的多态性，客户端可以选择使用任何一个具体算法类，并只需要维持一个数据类型是抽象算法类的对象。
	2. 一个对象有很多的行为，如果不用恰当的模式，这些行为就只好使用多重条件选择语句来实现。此时，使用策略模式，把这些行为转移到相应的具体策略类里面，就可以避免使用难以维护的多重条件选择语句。
	3. 不希望客户端知道复杂的、与算法相关的数据结构，在具体策略类中封装算法与相关的数据结构，可以提高算法的保密性与安全性。

### 4.10 模板方法模式（Template Method Pattern）
- **定义：** 定义一个操作中算法的框架，而将一些步骤延迟到子类中。模板方法模式使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。
	1. 某个方法的实现需要多个步骤（类似“请客”），其中有些步骤是固定的（类似“点单”和“买单”），而有些步骤并不固定，存在可变性（类似“吃东西”）。模板方法模式是基于继承的代码复用技术。

- **优点：** 
	1. 在父类中形式化地定义一个算法，而由它的子类来实现细节的处理，在子类实现详细的处理算法时并不会改变算法中步骤的执行次序。
	2. 模板方法模式是一种代码复用技术，它在类库设计中尤为重要，它提取了类库中的公共行为，将公共行为放在父类中，而通过其子类来实现不同的行为，它鼓励我们恰当使用继承来实现代码复用。
	3. 可实现一种反向控制结构，通过子类覆盖父类的钩子方法来决定某一特定步骤是否需要执行。
	4. 在模板方法模式中可以通过子类来覆盖父类的基本方法，不同的子类可以提供基本方法的不同实现，更换和增加新的子类很方便，符合单一职责原则和开闭原则。

- **缺点：** 需要为每一个基本方法的不同实现提供一个子类，如果父类中可变的基本方法太多，将会导致类的个数增加，系统更加庞大，设计也更加抽象，此时，可结合桥接模式来进行设计。

- **适用场景：** 
	1. 对一些复杂的算法进行分割，将其算法中固定不变的部分设计为模板方法和父类具体方法，而一些可以改变的细节由其子类来实现。即：一次性实现一个算法的不变部分，并将可变的行为留给子类来实现。
	2. 各子类中公共的行为应被提取出来并集中到一个公共父类中以避免代码重复。
	3. 需要通过子类来决定父类算法中某个步骤是否执行，实现子类对父类的反向控制。

### 4.11 访问者模式（Visitor Pattern）操作复杂对象结构
- **定义：** 提供一个作用于某对象结构中的各元素的操作表示，它使我们可以在不改变各元素的类的前提下定义作用于这些元素的新操作。

- **优点：** 
	1. 增加新的访问操作很方便。使用访问者模式，增加新的访问操作就意味着增加一个新的具体访问者类，实现简单，无须修改源代码，符合“开闭原则”。
	2. 将有关元素对象的访问行为集中到一个访问者对象中，而不是分散在一个个的元素类中。类的职责更加清晰，有利于对象结构中元素对象的复用，相同的对象结构可以供多个不同的访问者访问。
	3. 让用户能够在不修改现有元素类层次结构的情况下，定义作用于该层次结构的操作。

- **缺点：**
	1. 增加新的元素类很困难。在访问者模式中，每增加一个新的元素类都意味着要在抽象访问者角色中增加一个新的抽象操作，并在每一个具体访问者类中增加相应的具体操作，这违背了“开闭原则”的要求。
	2. 破坏封装。访问者模式要求访问者对象访问并调用每一个元素对象的操作，这意味着元素对象有时候必须暴露一些自己的内部操作和内部状态，否则无法供访问者访问。

- **适用场景：** 
	1. 一个对象结构包含多个类型的对象，希望对这些对象实施一些依赖其具体类型的操作。在访问者中针对每一种具体的类型都提供了一个访问操作，不同类型的对象可以有不同的访问操作。
	2. 需要对一个对象结构中的对象进行很多不同的并且不相关的操作，而需要避免让这些操作“污染”这些对象的类，也不希望在增加新操作时修改这些类。访问者模式使得我们可以将相关的访问操作集中起来定义在访问者类中，对象结构可以被多个不同的访问者类所使用，将对象本身与对象的访问操作分离。
	3. 对象结构中对象对应的类很少改变，但经常需要在此对象结构上定义新的操作。