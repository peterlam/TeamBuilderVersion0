<<Packages>>
Manager: It is pretty much main(). It trigger jaxb to parse a given xml file. Then call Builder and Evaluation respectively to generate team. Finally, calling the outputResult class to output the result.
Model: Holds input data in classes like: Team, Student, Response.
Builder: Contains the RandomTeamsBuilder class and AllCombinationTeamsBuilder class for two different way to initiate some team combinations.
Evaluation: Contains the MultipleChoiceEvaluation class and possibly 2 additional evaluation classes.
Output: output team building result
Jaxb: parser auto create classes to hold xml data






