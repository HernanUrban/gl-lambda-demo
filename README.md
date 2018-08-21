# AWS Lambda - Spring-Boot - DynamoDB with SAM support

0. Pre-req Install aws-cli and aws-sam-cli
```$ aws configure```
set your aws credentials

1. Clean and rebuild the code as a shaded jar, not as a Spring Boot jar.
```$ mvn clean package```

2. Create an S3 bucket to hold the application code. This bucket name must be unique across S3, so adjust for your use in the next two steps.
```$ aws s3 mb s3://gl-spring-boot-lambda-0817```

3. Copy the jar file to the S3 bucket and update the information into a SAM template.
```$ aws cloudformation package --template-file sam.yaml --output-template-file target/output-sam.yaml --s3-bucket gl-spring-boot-lambda-0817```

4. Deploy a Cloudformation stack from the SAM template. We must provide the --capabilities to allow the deploy to succeed because SAM will be creating IAM roles and policies needed to allow the API Gateway to execute the Lambda function.
```$ aws cloudformation deploy --template-file target/output-sam.yaml --stack-name gl-lambda-0817 --capabilities CAPABILITY_IAM```

5. Describe the stack, which will display the URL of the API in the outputs.
```$ aws cloudformation describe-stacks --stack-name gl-lambda-0817```

6. Update
```$ aws lambda list-functions```

```$ aws lambda update-function-code --function-name gl-spring-boot-lambda-081-LambdaSpringBootFunction-18J8PA1N9B34Y --zip-file fileb://target/gl-lambda-demo-0.0.1-SNAPSHOT.jar```

7. Delete
```$ aws cloudformation delete-stack --stack-name gl-lambda-0817```


## Conclusion:

Cold Start: first invocation of a Lambda function must start the Java Virtual Machine and Spring Boot.
Lambda warm: Subsequent executions, take less than 200 milliseconds for approximately five minutes.
You must keep these numbers in mind and decide if your application can afford the cold start times.
Cold start times may make Spring Boot a less than ideal choice for a Lambda accessed via API Gateway. It's certainly worth noting that it can be ideal for processing events triggered by S3 buckets, SNS, Kinesis Streams, and DynamoDB. In these scenarios, the cold start time of a few seconds is insignificant given that it isn't visible to a user.



_Links:_
https://github.com/awslabs/serverless-application-model/blob/master/versions/2016-10-31.md
https://docs.aws.amazon.com/es_es/lambda/latest/dg/serverless_app.html
https://github.com/awslabs/aws-serverless-java-container/wiki/Quick-start---Spring-Boot
https://dzone.com/articles/aws-lambda-with-spring-boot
https://github.com/awslabs/aws-sam-cli
https://docs.aws.amazon.com/es_es/lambda/latest/dg/test-sam-cli.html