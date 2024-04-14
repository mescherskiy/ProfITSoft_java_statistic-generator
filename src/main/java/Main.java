public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Error! Usage: java Main 'directory' 'attribute'");
            System.exit(1);
        }
        ProgramInterface.run(args[0], args[1]);
    }
}
