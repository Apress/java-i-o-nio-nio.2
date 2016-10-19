public class Employee
{
   private String name;

   private int empno;

   public Employee(String name, int empno)
   {
      this.name = name;
      this.empno = empno;
   }

   @Override
   public String toString()
   {
      return name + ": " + empno;
   }
}