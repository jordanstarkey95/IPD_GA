% Average best fitness

nr_runs = 10;
offset = 122;
interval = 103;

crossover_rates = [0.5, 0.6, 0.7, 0.8, 0.9];
mutation_rates = [0.001, 0.003, 0.005, 0.1, 0.3, 0.5];
memory_lengths = [1, 2, 3];
number_of_games = 64;

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
      filename = strcat('..\crossover_rate-', num2str(crossover_rate), 'mutation_rate-', num2str(mutation_rate), 'memory_length-', num2str(memory_length), 'number_of_games-', num2str(number_of_games),  '_summary.txt');
      total_best = 0;
      for l=0:(nr_runs-1)
        linenum = offset+l*interval;
        fid=fopen(filename);
        best_line = textscan(fid,'%s',1,'delimiter','\n', 'headerlines',linenum-1);
        fclose(fid);
        best_line_char = char(best_line);
        best = strsplit(best_line_char);
        total_best = total_best + str2num(char(best(2)));
      end
      average_best = total_best/nr_runs;
      z(i, j) = average_best;
    end
  end
  [X, Y] = meshgrid(crossover_rates, mutation_rates);
  Z = z';
  meshes(k) = mesh(X, Y, Z, 'EdgeColor', colors{k});
  hold on;
end

title('Average best fitness for different memory lengths', 'FontSize', 20);
xlabel('Crossover rate', 'FontSize', 16);
ylabel('Mutation rate', 'FontSize', 16);
leg = legend(meshes, {'1','2','3'}, 'Location', 'BestOutside', 'Orientation', 'Horizontal');
set(leg,'FontSize', 14);
saveas(gcf,'average_best_fitness.png')