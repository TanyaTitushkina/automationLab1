package com.alliedtesting.lab1;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class JsonParser {

    private static final File inputFile = new File("employees.json");

    public static void main(String[] args) {

        System.out.println("*** Parse JSON file ***\n");
        parseJsonFile(inputFile);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n*** Add new department ***\n");
        createNewDep(inputFile);
    }

    public static void parseJsonFile (File inputFile) {

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(inputFile));

            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject); //full json file content

            JSONArray company = (JSONArray) jsonObject.get("company");

            if (company == null) {
                System.out.println("There are no \"company\" elements in employees.json file.");
                return;
            }

            Iterator<JSONObject> depIterator = company.iterator();

            while (depIterator.hasNext()) {

                JSONObject department = (JSONObject) depIterator.next().get("department");

                if (department == null) {
                    System.out.println("There are no \"department\" elements in employees.json file.");
                    return;
                }

                System.out.println("\n----------------------------\n");

                if(department.get("_depId") != null) {
                    System.out.print("Department " + department.get("_depId"));
                } else {
                    System.out.println("The department's id is undefined.");
                }

                if(department.get("_name") != null) {
                    System.out.println(" : " + department.get("_name"));
                } else {
                    System.out.println("The department's name is undefined.");
                }

                JSONArray employees = (JSONArray) department.get("employee");
                if (employees == null) {
                    System.out.println("There are no employees in department " + department.get("_name"));
                }

                Iterator<JSONObject> empIterator = employees.iterator();

                while (empIterator.hasNext()) {

                    JSONObject employee = empIterator.next();

                    if (employee.get("_empId") != null) {
                        System.out.println("\nEmployee ID : " + employee.get("_empId"));
                    } else {
                        System.out.println("Employee's id is undefined.");
                    }

                    if (employee.get("firstName") != null) {
                        System.out.println("First Name : " + employee.get("firstName"));
                    } else {
                        System.out.println("Employee's first name is undefined.");
                    }

                    if (employee.get("lastName") != null) {
                        System.out.println("Last Name : " + employee.get("lastName"));
                    } else {
                        System.out.println("Employee's last name is undefined.");
                    }

                    if (employee.get("birthDate") != null) {
                        System.out.println("Date of birth : " + employee.get("birthDate"));
                    } else {
                        System.out.println("Employee's birth date is undefined.");
                    }

                    if (employee.get("position") != null) {
                        System.out.println("Position : " + employee.get("position"));
                    } else {
                        System.out.println("Employee's position is undefined.");
                    }

                    if (employee.get("skill") != null) {
                        JSONArray skill = (JSONArray) employee.get("skill");
                        Iterator<JSONObject> skillIterator = skill.iterator();

                        System.out.println("Skills : ");

                        while (skillIterator.hasNext()) {
                            System.out.println("        " + skillIterator.next());
                            }
                    } else {
                        System.out.println("Employee's skills are undefined.");
                    }

                    if (employee.get("managerId") != null) {
                        System.out.println("Manager ID : " + employee.get("managerId"));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public static void createNewDep (File inputFile) {

        JSONParser parser = new JSONParser();

        try {
            JSONObject newEmp = new JSONObject();
            newEmp.put("_empId", "301");
            newEmp.put("lastName", "Waters");
            newEmp.put("firstName", "Roger");
            newEmp.put("birthDate", "16.01.1977");
            newEmp.put("position", "Department Manager");

            JSONArray skills = new JSONArray();
            skills.add("Knows all about new web design trends");
            newEmp.put("skill", skills);

            newEmp.put("managerId", "0");

            JSONArray employees = new JSONArray();
            employees.add(newEmp);

            JSONObject newDepDetails = new JSONObject();
            newDepDetails.put("_name", "Marketing");
            newDepDetails.put("_depId", "3");
            newDepDetails.put("employee", employees);

            JSONObject newDep = new JSONObject();
            newDep.put("department", newDepDetails);

            System.out.println("New department is created.");

            Object obj = parser.parse(new FileReader(inputFile));

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray company = (JSONArray) jsonObject.get("company");

            if (company == null) {
                System.out.println("There are no \"company\" elements in employees.json file.");
                return;
            }

            company.add(2, newDep);
            jsonObject.put("company", company);

            PrintWriter pw = null;
            try {
                pw = new PrintWriter(inputFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
                pw.write(jsonObject.toJSONString());
                pw.flush();
                pw.close();

            System.out.println("New department is added to JSON file\n");
            parseJsonFile(inputFile); // view all company's departments with the new one

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
