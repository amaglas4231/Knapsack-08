## How it works

1. Run the App class
2. Choose between 6 options
    - Parcels Exact Cover Problem
    - Depth first search on maximizing the value of container with parcels
    - Simulated annealing on maximizing the value of container with parcels
    - Pentominoes Exact Cover Problem
    - Depth first search on maximizing the value of container with pentominoes
    - Simulated annealing on maximizing the value of container with pentominoes

Each algorithm (Genetic algorithm, Depth search and Simulated annealing) can be run in it's specific file through the `main` methods of each class. This way the algorithms can run with either 'pentominoes' or 'abc parcels' and the amount of each piece can be changed for different results.

## Structure of the code

In src we have 4 folders:

- Core - for the helping classes:
                        <Container.java>
                        <ParcelList.java>
                        <Parcels.java>
                        <Pentominoes.java>
                        <PentominoeList.java>
                        <Rotations.java>
                        <UserVariables.java>
- GeneticA - for the genetic algorithm:
                        <DepthSearch.java>
                        <GeneticAlgorithm.java>
                        <PopulationLea.java>
- SimulatedAnnealingA - for the simulated annealing algorithm:
                        <SimulatedAnnealing.java>
- UI - for the user interface classes:
                        <App.java>
                        <CSS.css>
                        <DSParcelsExactCoverVisual.java>
                        <DSParcelsMaxVisual.java>
                        <DSPentosExactCoverVisual.java>
                        <DSPentosMaxVisual.java>
                        <menu.fxml>
                        <OurMenu.java>
                        <SAParcelsMaxVisual.java>
                        <SAPentosMaxVisual.java>
                        <Shape.java>
