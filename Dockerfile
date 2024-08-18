# Use the official Tomcat base image
FROM tomcat:9.0

# Remove the default webapps to avoid conflicts
RUN rm -rf /C:/WebServer/TomCat9/webapps

# Copy the WAR file to the webapps directory
COPY target/MVC2.war /C:/WebServer/TomCat9/webapps

# Expose port 8080
EXPOSE 8080