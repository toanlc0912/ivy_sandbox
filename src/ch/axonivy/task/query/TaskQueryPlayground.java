package ch.axonivy.task.query;

import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class TaskQueryPlayground {

	public static void main(String[] args) {
		TaskQuery orConditions = TaskQuery.create().where().state()
				.isEqual(TaskState.SUSPENDED).or().state()
				.isEqual(TaskState.RESUMED).or().state()
				.isEqual(TaskState.PARKED).orderBy().priority();

		TaskQuery query1 = TaskQuery.create().where().and(orConditions).orderBy().priority();

		TaskQuery query2 = TaskQuery.fromJson(query1.asJson()); // The result
																// queries of 1
																// and 2 are the
																// same.
		
		System.out.println("Query 1 - no filter\n" + query1); // Correct as expected.
		System.out.println("Query 2 - no filter\n" + query2); // Wrong, the same as Way 1.
		
		System.out.println("================================================================================");
		TaskQuery query3 = TaskQuery.fromJson(orConditions.asJson()).where().and().applicationId().isEqual(1);
		
		
		
		System.out.println("Query 1 \n" + query1.where().applicationId().isEqual(1)); // Correct as expected.
		System.out.println("Query 2 \n" + query2.where().applicationId().isEqual(1)); // Wrong, the same as Way 1.
		System.out.println("Query 3 \n" + query3.toString());
		System.out.println("Query 3 \n" + query3.asJson());

	}

}
