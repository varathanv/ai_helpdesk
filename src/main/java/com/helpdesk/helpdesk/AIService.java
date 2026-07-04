package com.helpdesk.helpdesk.service;


import org.springframework.stereotype.Service;


@Service
public class AIService {


    public String analyzeProblem(String problem){


        String text = problem.toLowerCase();



        if(text.contains("vpn")
        || text.contains("network")
        || text.contains("internet")){


            return "VPN,Network Team,HIGH,Check internet connection and restart VPN client";

        }



        else if(text.contains("laptop")
        || text.contains("printer")
        || text.contains("keyboard")
        || text.contains("mouse")){


            return "Hardware,Hardware Team,MEDIUM,Check device connection and restart the hardware";

        }



        else if(text.contains("software")
        || text.contains("application")
        || text.contains("error")){


            return "Software,Software Team,MEDIUM,Try restarting the application and check for errors";

        }



        else if(text.contains("password")
        || text.contains("account")
        || text.contains("login")){


            return "Account,Admin Team,LOW,Reset password or verify account details";

        }



        else{


            return "General,Admin Team,LOW,Contact IT support for further help";

        }


    }

public String getSolution(String problem){


problem = problem.toLowerCase();



if(problem.contains("password")){


return "Try resetting your password. If account is locked contact Admin Team";


}



else if(problem.contains("vpn")){


return "Check internet connection, restart VPN client and try again";


}



else if(problem.contains("wifi")
|| problem.contains("network")){


return "Restart router and check network connection";


}






else{

return "I could not solve this automatically. Please create a ticket and IT team will help you.";

}





}

}