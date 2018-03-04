package com.identity.services.listeners;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class IdentityPropertiesLoader implements ServletContextListener
{

   @Override
   public void contextDestroyed(final ServletContextEvent event)
   {
   }

   @Override
   public void contextInitialized(final ServletContextEvent event)
   {
      final String props = "/identity.properties";
      final Properties propsFromFile = new Properties();
      try
      {
         propsFromFile.load(getClass().getResourceAsStream(props));
      }
      catch (final IOException e)
      {
    	  System.out.println(e);
          e.printStackTrace();
      }
      for (String prop : propsFromFile.stringPropertyNames())
      {
         if (System.getProperty(prop) == null)
         {
             System.setProperty(prop, propsFromFile.getProperty(prop));
         }
      }
   }
}  