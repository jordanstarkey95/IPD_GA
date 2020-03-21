% Experiment 5 - Count

% Average average fitness

nr_runs = 10;
offset = 122;
interval = 103;

crossover_rates = [0.5, 0.6, 0.7, 0.8, 0.9];
mutation_rates = [0.001, 0.002, 0.003, 0.004, 0.005];
memory_lengths = [1];
number_of_games = 64;

for k=1:length(memory_lengths)
  fprintf('Memory length: %d\n', memory_lengths(k));
  best_chromosomes = cell(nr_runs*length(crossover_rates)*length(mutation_rates), 1);
  n = 1;
  for i=1:length(crossover_rates)
    for j=1:length(mutation_rates)    
      crossover_rate = crossover_rates(i);
      mutation_rate = mutation_rates(j);
      memory_length = memory_lengths(k);
      
      % Source: https://www.mathworks.com/matlabcentral/answers/306876-how-do-i-read-only-a-specific-line-while-reading-a-text-file-in-matlab
      % Author: MathWorks Support Team 
      % Date Published: 12 October 2016
      % Date Accessed: 18 March 2020
      for l=0:(nr_runs-1)
        linenum = offset+l*interval;
        fid=fopen(strcat('..\experiment-2crossover_rate-', num2str(crossover_rate, 1), 'mutation_rate-', num2str(mutation_rate, 3), 'memory_length-', num2str(k), 'number_of_games-', num2str(number_of_games),  '_summary.txt')); 
        best_line = textscan(fid,'%s',1,'delimiter','\n', 'headerlines',linenum-1);
        best_line_char = char(best_line);
        best = strsplit(best_line_char);
        best_chromosomes{n} = best{1};
        n = n+1;
      end
    end
  end
  
  % Source: https://nl.mathworks.com/matlabcentral/answers/115838-count-occurrences-of-string-in-a-single-cell-array-how-many-times-a-string-appear
  % Author: Azzi Abdelmalek
  % Date Published: 12 February 2014
  % Date Accessed: 21 March 2020
  unique_chromosomes = unique(best_chromosomes);
  count_chromosomes = cellfun(@(x) sum(ismember(best_chromosomes,x)), unique_chromosomes,'un',0)
end