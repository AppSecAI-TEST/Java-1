package DemoApp;


/**
* DemoApp/DemoPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from demo.idl
* Monday, October 27, 2014 3:55:44 PM EST
*/

public abstract class DemoPOA extends org.omg.PortableServer.Servant
 implements DemoApp.DemoOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("randomChar", new java.lang.Integer (0));
    _methods.put ("shutdown", new java.lang.Integer (1));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // DemoApp/Demo/randomChar
       {
         char $result = (char)0;
         $result = this.randomChar ();
         out = $rh.createReply();
         out.write_char ($result);
         break;
       }

       case 1:  // DemoApp/Demo/shutdown
       {
         this.shutdown ();
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:DemoApp/Demo:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Demo _this() 
  {
    return DemoHelper.narrow(
    super._this_object());
  }

  public Demo _this(org.omg.CORBA.ORB orb) 
  {
    return DemoHelper.narrow(
    super._this_object(orb));
  }


} // class DemoPOA
