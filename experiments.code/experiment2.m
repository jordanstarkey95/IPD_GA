% Experiment 2 - Parameters

% Average average fitness

crossover_rates = [0.5, 0.6, 0.7, 0.8, 0.9];
mutation_rates = [0.001, 0.002, 0.003, 0.004, 0.005, 0.010];
memory_lengths = [1, 2, 3, 4, 5, 6];
number_of_games = 64;

% Read line 1052 in the summary file
linenum = 1052;

best_fitness = -1;
best_chromosome = -1;
best_crossover_rate = -1;
best_mutation_rate = -1;
best_memory_length = -1;

for i=1:length(crossover_rates)
  for j=1:length(mutation_rates)
    for k=1:length(memory_lengths)
      crossover_rate = crossover_rates(i);
      mutation_rate = mutation_rates(j);
      memory_length = memory_lengths(k);
      
      % data = dlmread(strcat('experiment-2crossover_rate-', num2str(crossover_rate, 1), 'mutation_rate-', num2str(mutation_rate, 3), 'memory_length-', num2str(k), 'number_of_games-', num2str(number_of_games),  '_BestAndAverageGeneration.csv'), ',', 0, 0);
      % Source: https://www.mathworks.com/matlabcentral/answers/306876-how-do-i-read-only-a-specific-line-while-reading-a-text-file-in-matlab
      % Author: MathWorks Support Team 
      % Date Published: 12 October 2016
      % Date Accessed: 18 March 2020
      fid=fopen(strcat('..\experiment-2crossover_rate-', num2str(crossover_rate, 1), 'mutation_rate-', num2str(mutation_rate, 3), 'memory_length-', num2str(memory_length), 'number_of_games-', num2str(number_of_games),  '_summary.txt')); 
      best_line = textscan(fid,'%s',1,'delimiter','\n', 'headerlines',linenum-1);
      best_line_char = char(best_line);
      best = strsplit(best_line_char);
      fitness = str2num(char(best(2)));
      
      if fitness > best_fitness
        best_fitness = fitness;
        best_chromosome = char((best(1)));
        best_crossover_rate = crossover_rate;
        best_mutation_rate = mutation_rate;
        best_memory_length = memory_length;
      end
    end
  end
end

fprintf('Best fitness: %d\n', best_fitness);
fprintf('Best chromosome: %s\n', best_chromosome);
fprintf('Crossover rate: %f\n', best_crossover_rate);
fprintf('Mutation rate: %f\n', best_mutation_rate);
fprintf('Memory length: %d\n', best_memory_length);