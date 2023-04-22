package com.example.controller;


import com.example.service.GraphQLService;
import com.example.entity.GraphQLRequestBody;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class MainController {
    @Autowired
    private GraphQLService graphQLService;

    @Autowired
    private GraphQL graphQL;

    @PostMapping(value = "/graphql")
    private ResponseEntity<ExecutionResult> executeGraphQL(@RequestBody String query)
    {
        try {
            System.out.println(" Inside Controller " + query);
            ExecutionResult executionResult = graphQLService.getGraphQL().execute(query);
            return new ResponseEntity<>(executionResult, HttpStatus.OK);
        }
        catch (Exception e)
        {
            System.out.println(" Exception " + e);
            return  null;
        }
    }

    @PostMapping(value = "/graphql1")
    private Mono<Map<String, Object>> executeGraphQL(@RequestBody GraphQLRequestBody graphQLRequestBody)
    {
        System.out.println(" Inside GraphQL POJO Method");
        System.out.println(" Query "+ graphQLRequestBody.getQuery() + " Operation "+ graphQLRequestBody.getOperationName() + " Variables " + graphQLRequestBody.getVariables());

        Map<String , Object> obj = new HashMap<>();

        if(graphQLRequestBody.getVariables() == null)
        {
            obj = Collections.emptyMap();
        }
        else {
            obj = graphQLRequestBody.getVariables();
        }

        return Mono.fromCompletionStage(graphQL.executeAsync(ExecutionInput.newExecutionInput()
                        .query(graphQLRequestBody.getQuery()).operationName(graphQLRequestBody.getOperationName())
                        .variables(obj).build()))
                .map(ExecutionResult::toSpecification);
    }
}

