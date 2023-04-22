package com.example.service;


import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Service
public class GraphQLService {

    @Value("classpath:/graphql/schema.graphql")
    private Resource resource;


    @Autowired
    private FetchAllDoctorDetails fetchAllDoctorDetails;


    private GraphQL graphQL;

    private GraphQL graphQL1;

    @PostConstruct
    private void getSchema() throws IOException {
        File schema = resource.getFile();

        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schema);

        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type("Query" , typewiring ->typewiring
                        .dataFetcher("getDoctorDetails",fetchAllDoctorDetails))
                .build();

        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry , runtimeWiring);
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    @Bean
    private GraphQL graphQLBean() throws IOException {

        System.out.println(" Bean Creation Strated");
        // Schema parser 1st Step
        SchemaParser schemeParser = new SchemaParser();
        ClassPathResource classPathResource = new ClassPathResource("graphql/schema.graphql");
        TypeDefinitionRegistry typeDefinitionRegistry = schemeParser.parse(classPathResource.getInputStream());

        // Wiring 2nd Step
        RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
                .type(TypeRuntimeWiring.newTypeWiring("query").dataFetcher("getDoctorDetails",fetchAllDoctorDetails))
                .build();

        // Combining Schema Parser and Wiring
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry , runtimeWiring);
        GraphQL graphQL1 = GraphQL.newGraphQL(graphQLSchema).build();
        return  graphQL1;
    }

    public GraphQL getGraphQL()
    {
        return graphQL;
    }

    public GraphQL getGraphQLBean()
    {
        return graphQL1;
    }

    
}
