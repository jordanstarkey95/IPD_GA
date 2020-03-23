% Experiment 3 - Parameters

% Average average fitness

crossover_rates = [0.05, 0.50, 0.60, 0.70, 0.80, 0.90];
mutation_rates = [0.001, 0.002, 0.003, 0.004, 0.005, 0.010, 0.050, 0.500];
memory_lengths = [1, 2, 3];
number_of_games = 64;

% Read line 1052 in the summary file
linenum = 1052;

meshes = zeros(length(memory_lengths));
colors = {'red', 'green', 'blue'};

for k=1:length(memory_lengths)
  z = zeros(length(crossover_rates), length(mutation_rates));
  for i=1:length(crossover_rates)
    for j=1:length(mutation_rates)    
      crossover_rate = crossover_rates(i);
      mutation_rate = mutation_rates(j);
      memory_length = memory_lengths(k);
      
      % Source: https://www.mathworks.com/matlabcentral/answers/306876-how-do-i-read-only-a-specific-line-while-reading-a-text-file-in-matlab
      % Author: MathWorks Support Team 
      % Date Published: 12 October 2016
      % Date Accessed: 18 March 2020
      fid=fopen(strcat('..\experiment-2crossover_rate-', num2str(crossover_rate, 1), 'mutation_rate-', num2str(mutation_rate, 3), 'memory_length-', num2str(k), 'number_of_games-', num2str(number_of_games),  '_summary.txt')); 
      best_line = textscan(fid,'%s',1,'delimiter','\n', 'headerlines',linenum-1);
      best_line_char = char(best_line);
      best = strsplit(best_line_char);
      z(i, j) = str2num(char(best(2)));
    end
  end
  [X, Y] = meshgrid(crossover_rates, mutation_rates);
  Z = z';
  meshes(k) = mesh(X, Y, Z, 'EdgeColor', colors{k});
  hold on;
end

title('Best fitness for different memory lengths', 'FontSize', 24);
xlabel('Crossover rate', 'FontSize', 16);
ylabel('Mutation rate', 'FontSize', 16);
leg = legend(meshes, {'1','2','3'}, 'Location', 'BestOutside', 'Orientation', 'Horizontal');
set(leg,'FontSize', 14);
saveas(gcf,'experiment3fixed.png')