package com.rules.rulesapi;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.drools.template.jdbc.ResultSetGenerator;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rules.DAO.database;
import com.rules.Entities.ChargeManagment;
import com.rules.Entities.RulesOutput;
public class Program {
		
	private static final Logger LOGGER = LoggerFactory.getLogger(ChargeManagment.class);
	
	public static ArrayList<RulesOutput> getRules() throws Exception
	{
		Statement st=database.getStatement();
		ResultSet rs=st.executeQuery("select category,operation_type,condition_type,limit_to,limit_from,fees_value,fees_type from bank_rules ");
	    ArrayList<RulesOutput> rules=new ArrayList();
		while(rs.next()) {
			RulesOutput output=new RulesOutput(rs.getString("category"),rs.getString("operation_type"));
			output.setCondition(rs.getInt("limit_from"),rs.getInt("limit_to") ,rs.getString("condition_type"));
			output.setFees(rs.getDouble("fees_value"),rs.getString("fees_type"));
			rules.add(output);
		}
		return rules;
	}
	
      public static ChargeManagment getFees(ChargeManagment account)throws Exception {
    	  try {
    		  Statement ps=database.getStatement();
    		  ResultSet rs=ps.executeQuery("select category,operation_type,condition_type,limit_to,"
    		  		+ "limit_from,fees_value,fees_type from bank_rules ");
    		  final ResultSetGenerator converter = new ResultSetGenerator();
    		  final String drl = converter.compile(rs,getRulesStream());
    		  LOGGER.info(drl);
    		  KieSession ksession = createKieSessionFromDRL(drl);
    		  ChargeManagment c1 = account;
    		  ksession.insert(c1); 
    		  ksession.fireAllRules(1);
    		  LOGGER.info(String.valueOf(c1.getTax()));
    		  return account;
    	  }
    	 catch(Exception ex)
    	  {
    		 LOGGER.error("some error occurred while firing rules. "+ex.getMessage()); 
    		 return null;
    	  }
      }

	private static KieSession createKieSessionFromDRL(String drl) {
		KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);
        
        Results results = kieHelper.verify();
        
        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                LOGGER.error("Error: "+message.getText());
            }
            
            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }
        
        return kieHelper.build().newKieSession();
    }
      
	private static InputStream getRulesStream() throws FileNotFoundException {
			//modify according to path
            return new FileInputStream("C:/Users/hp/git/Neural-Hack5/rulesapi/bin/src/main/resources/rules1.drt");
      }
}