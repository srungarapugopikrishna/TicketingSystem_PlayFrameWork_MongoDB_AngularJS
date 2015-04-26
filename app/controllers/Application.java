package controllers;

import java.util.*;
import com.google.common.collect.ArrayListMultimap;
import models.Ticket;
import models.User;
import play.*;
import play.api.libs.json.JacksonJson;
import play.data.Form;
import play.mvc.*;
import scala.math.Ordering;
import views.html.*;
import play.mvc.Http.Context;
import static play.data.Form.form;
import play.modules.mongodb.jackson.MongoDB;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;
import org.codehaus.jackson.annotate.JsonProperty;


public class Application extends Controller {

    final static Form<User> userForm=form(User.class);
//     final static Form<User> userForm1=form(User.class);
     static String userID;
    final static Form<Ticket> userTicket=form(Ticket.class);

    static JacksonDBCollection<User,String> usersDetails = MongoDB.getCollection("logindetails",User.class,String.class);

    public static Result index() {

        return ok(index.render(userForm));
    }

    public static Result log_in_Validation(){
        Form<User> filledForm=userForm.bindFromRequest();
        User created=filledForm.get();
        userID=created.username;
//        List<User> loginlist=usersDetails.find().toArray();
        Logger.info("A log message");
    //    int a=loginlist.indexOf(created.password);
        if(created.username.equals(created.password)){
               return ok(Emp_List_Func.render(created.username));
        }else{
               return ok(submit.render("Invalid Details"));
        }
    }
    public static Result main_menu(){
        return ok(Emp_List_Func.render(userID));
    }
    public static Result Dislpay_Tickets(){
        return ok(Display_Tickets.render());
    }
    public static Result Log_Ticket_Page(){
        Http.Context.current().args.put("empID",userID);
        return ok(Log_Ticket.render(userTicket));
    }

    public static Result Log_Ticket(){
        Form<Ticket> logTicket=userTicket.bindFromRequest();
        Ticket created=logTicket.get();
        return ok(TestPage.render("UserName: "+created.custid+"\n Logged depatments: "+created.empid+created.logdepts));
    }

    public static Result Assign_Ticket_Page(){
        return ok(Assign_Ticket.render());
    }
}
