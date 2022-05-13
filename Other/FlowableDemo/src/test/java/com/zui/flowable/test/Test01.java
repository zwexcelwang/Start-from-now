package com.zui.flowable.test;

import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.impl.TaskBuilderImpl;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test01 {

    /**
     * 获取流程引擎对象
     */
    @Test
    public void testProcessEngine() {
        ProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration();
        configuration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/flowable?serverTimezone=UTC&characterEncoding=UTF-8&nullCatalogMeansCurrent=true");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("root");
        //如果数据库中的表结构不存在就新建
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine processEngine = configuration.buildProcessEngine();
        System.out.println("ProcessEngine is : " + processEngine);
    }

    ProcessEngineConfiguration configuration = null;
    @Before
    public void before() {
        configuration = new StandaloneProcessEngineConfiguration();
        configuration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/flowable?serverTimezone=UTC&characterEncoding=UTF-8&nullCatalogMeansCurrent=true");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("root");
        //如果数据库中的表结构不存在就新建
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

    }

    /*
    部署流程
     */
    @Test
    public void testDeploy() {
        ProcessEngine processEngine = configuration.buildProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("holiday-request.bpmn20.xml")  //关联要部署的流程文件
                .deploy();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        System.out.println("Found process definition : " + processDefinition.getId());
        System.out.println("Found process definition : " + processDefinition.getName());
    }

    /*
    删除流程
     */
    @Test
    public void testDeleteDeploy() {
        ProcessEngine processEngine = configuration.buildProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //删除部署的流程，第一个参数是id，如果部署的流程启动了就不允许删除了
//        repositoryService.deleteDeployment("1");
        //第二个参数是级联删除，如果流程启动了，相关的任务一定会被删除
        repositoryService.deleteDeployment("7501", true);
    }

    /*
    启动运行实例
     */
    @Test
    public void testRunProcess() {
        ProcessEngine processEngine = configuration.buildProcessEngine();

        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employee", "lisi");
        variables.put("nrOfHolidays", 6);
        variables.put("description", "放假放假放假！！！");
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("holidayRequest", variables);
        System.out.println("Definition:" + processInstance.getProcessDefinitionId());
        System.out.println("processInstance.getDescription() = " + processInstance.getDescription());

    }

    @Test
    public void testQueryTask() {
        ProcessEngine processEngine = configuration.buildProcessEngine();
        TaskService taskService = processEngine.getTaskService();
//        List<Task> taskList = taskService.createTaskQuery()
//                        .processDefinitionKey("holidayRequest") //指定查询流程
//                        .taskAssignee("zhangsan") //查询这个任务的处理人
//                        .list();
        List<Task> taskList = taskService.createTaskQuery().taskCandidateGroup("managers").list();
        for(Task task:taskList) {
            System.out.println("task.getProcessDefinitionId(): " + task.getProcessDefinitionId());
            System.out.println("task.getId(): " + task.getId());
            System.out.println("task.getName(): " + task.getName());
            System.out.println("task.getAssignee(): " + task.getAssignee());
            System.out.println("task.getDescription(): " + task.getDescription());
            Map<String, Object> processVariables = taskService.getVariables(task.getId());
            System.out.println(processVariables.get("employee") + " wants " +
                    processVariables.get("nrOfHolidays") + " of holidays. Do you approve this?");
        }

    }
    @Test
    public void testCompleteTask() {
        ProcessEngine processEngine = configuration.buildProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().taskCandidateGroup("managers").singleResult();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("approved", true);
        //完成任务
        taskService.complete(task.getId(), variables);

    }

    @Test
    public void testHistory() {
        ProcessEngine processEngine = configuration.buildProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricActivityInstance> historicActivityInstanceList =
                historyService.createHistoricActivityInstanceQuery()
                        .processDefinitionId("holidayRequest:1:12503")
                        .finished() //查询的历史记录状态是已经完成
                        .orderByHistoricActivityInstanceEndTime().asc() //排序字段
                        .list();
        System.out.println("SIZE:" + historicActivityInstanceList.size());
        for (HistoricActivityInstance history : historicActivityInstanceList) {
            System.out.println("history.getActivityName():" + history.getActivityName() + " " +  history.getActivityId() + " took "
                    + history.getDurationInMillis() + " milliseconds");
        }

    }
}
